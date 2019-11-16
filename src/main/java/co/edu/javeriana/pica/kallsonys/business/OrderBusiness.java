package co.edu.javeriana.pica.kallsonys.business;

import co.edu.javeriana.pica.kallsonys.dal.repository.CountryRepository;
import co.edu.javeriana.pica.kallsonys.dal.repository.CustomerRepository;
import co.edu.javeriana.pica.kallsonys.dto.MonthlyOrderReport;
import co.edu.javeriana.pica.kallsonys.enums.OrderStatusEnum;
import co.edu.javeriana.pica.kallsonys.dal.entity.*;
import co.edu.javeriana.pica.kallsonys.dal.repository.OrderRepository;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderBusiness {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Long createOrder(co.edu.javeriana.pica.kallsonys.dto.Order orderDTO) throws KallSonysException {
        Optional<Country> countryOptional =
                countryRepository.findById(orderDTO.getAddress().getCountryCode());
        if (!countryOptional.isPresent()) {
            throw new KallSonysException("No se tiene convenio con el País especificado.");
        }

        Optional<Customer> customerOptional = customerRepository.findById(orderDTO.getIdCustomer());
        if (!customerOptional.isPresent()) {
            throw new KallSonysException("El Cliente no existe.");
        }

        Order order = new Order();
        order.setDate(LocalDate.now());

        order.setStatus(new Status());
        order.getStatus().setId(OrderStatusEnum.EN_VALIDACION.getId());
        order.setStatusDate(LocalDate.now());

        order.setComments(orderDTO.getComments());

        order.setAddress(new Address());
        order.getAddress().setStreet(orderDTO.getAddress().getStreet());
        order.getAddress().setZip(orderDTO.getAddress().getZip());
        order.getAddress().setState(orderDTO.getAddress().getState());
        order.getAddress().setCity(orderDTO.getAddress().getCity());
        order.getAddress().setCountry(countryOptional.get());

        order.setCustomer(customerOptional.get());

        BigDecimal totalPrice = BigDecimal.ZERO;

        order.setItems(new ArrayList<>());
        for (co.edu.javeriana.pica.kallsonys.dto.Item itemDTO : orderDTO.getItems()) {
            Item item = new Item();
            item.setPrice(itemDTO.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            item.setProductCode(itemDTO.getProductCode());
            item.setOrder(order);
            order.getItems().add(item);

            totalPrice = totalPrice.add(itemDTO.getPrice());
        }

        order.setPrice(totalPrice);

        return orderRepository.save(order).getId();
    }

    public co.edu.javeriana.pica.kallsonys.dto.Order findById(Long id) throws KallSonysException {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new KallSonysException("La Orden no existe.");
        }
        return orderEntityToOrderDTO(orderOptional.get());
    }

    public List<co.edu.javeriana.pica.kallsonys.dto.Order> findOrdersByProductCode(String productCode)
            throws KallSonysException {
        if (productCode == null || productCode.isEmpty()) {
            throw new KallSonysException("El Código del Producto es requerido.");
        }

        return orderEntityListToOrderDTOList(orderRepository.findOrdersByProductCode(productCode));
    }

    public MonthlyOrderReport findMonthlyReportByMonthAndStatus(Integer year, Integer month, Integer statusId) throws KallSonysException {
        if (statusId == null || month == null) {
            throw new KallSonysException("El Id del Estado de la Orden y el número del Mes son requeridos.");
        }

        MonthlyOrderReport monthlyOrderReport = new MonthlyOrderReport();
        List<Object[]> report = orderRepository.findMonthlyReportByMonthAndStatus(month + "-" + year, statusId);
        if (!report.isEmpty()) {
            monthlyOrderReport.setQuantity(((BigDecimal)report.get(0)[0]).intValue());
            monthlyOrderReport.setTotal((BigDecimal)report.get(0)[1]);
        }
        return monthlyOrderReport;
    }

    public List<co.edu.javeriana.pica.kallsonys.dto.Order> findAllByStatusAndOrderedByDate(
            Integer statusId, String ordering, int page, int results) {

        Sort sort = null;
        if (ordering == null || ordering.isEmpty() || ordering.toUpperCase().equals("ASC")
                || !ordering.toUpperCase().equals("DESC")) {
            sort = Sort.by("date").ascending();
        } else {
            sort = Sort.by("date").descending();
        }
        Pageable sortedByDate = PageRequest.of(page, results, sort);
        return orderEntityListToOrderDTOList(orderRepository.findAllByStatusId(statusId, sortedByDate));
    }

    public List<co.edu.javeriana.pica.kallsonys.dto.Order> ordersPaymentRankingByStatusBetweenDates(
            Integer statusId, LocalDate startDate, LocalDate endDate, String ordering, int page, int results) {
        Sort sort = null;
        if (ordering == null || ordering.isEmpty() || ordering.toUpperCase().equals("ASC")
                || !ordering.toUpperCase().equals("DESC")) {
            sort = Sort.by("price").ascending();
        } else {
            sort = Sort.by("price").descending();
        }
        Pageable sortedByPrice = PageRequest.of(page, results, sort);
        return orderEntityListToOrderDTOList(
                orderRepository.findByStatusIdAndDateBetween(statusId, startDate, endDate, sortedByPrice));
    }

    private co.edu.javeriana.pica.kallsonys.dto.Order orderEntityToOrderDTO(Order order) {
        co.edu.javeriana.pica.kallsonys.dto.Order orderDTO = new co.edu.javeriana.pica.kallsonys.dto.Order();
        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setComments(order.getComments());
        orderDTO.setAddress(new co.edu.javeriana.pica.kallsonys.dto.Address());
        orderDTO.getAddress().setStreet(order.getAddress().getStreet());
        orderDTO.getAddress().setZip(order.getAddress().getZip());
        orderDTO.getAddress().setState(order.getAddress().getState());
        orderDTO.getAddress().setCity(order.getAddress().getCity());
        orderDTO.getAddress().setCountryCode(order.getAddress().getCountry().getCode());
        orderDTO.setIdCustomer(order.getCustomer().getId());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setItems(new ArrayList<>());
        for (Item item : order.getItems()) {
            co.edu.javeriana.pica.kallsonys.dto.Item itemDTO = new co.edu.javeriana.pica.kallsonys.dto.Item();
            itemDTO.setId(item.getId());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setProductCode(item.getProductCode());
            orderDTO.getItems().add(itemDTO);
        }
        orderDTO.setStatus(new co.edu.javeriana.pica.kallsonys.dto.Status());
        orderDTO.getStatus().setId(order.getStatus().getId());
        orderDTO.getStatus().setName(order.getStatus().getName());
        orderDTO.getStatus().setDate(order.getDate());

        return orderDTO;
    }

    private List<co.edu.javeriana.pica.kallsonys.dto.Order> orderEntityListToOrderDTOList(List<Order> orders) {
        List<co.edu.javeriana.pica.kallsonys.dto.Order> orderDTOs = new ArrayList<>();
        if (orders != null) {
            for (Order order : orders) {
                orderDTOs.add(orderEntityToOrderDTO(order));
            }
        }
        return orderDTOs;
    }
}
