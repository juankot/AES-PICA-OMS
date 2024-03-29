package co.edu.javeriana.pica.kallsonys.business;

import co.edu.javeriana.pica.kallsonys.dal.entity.Customer;
import co.edu.javeriana.pica.kallsonys.dal.entity.CustomerType;
import co.edu.javeriana.pica.kallsonys.dal.entity.IdentificationCardType;
import co.edu.javeriana.pica.kallsonys.dal.repository.CustomerRepository;
import co.edu.javeriana.pica.kallsonys.dal.repository.CustomerTypeRepository;
import co.edu.javeriana.pica.kallsonys.dal.repository.IdentificationCardTypeRepository;
import co.edu.javeriana.pica.kallsonys.dto.CustomerPayment;
import co.edu.javeriana.pica.kallsonys.dto.GenericPage;
import co.edu.javeriana.pica.kallsonys.enums.CustomerTypeEnum;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerBusiness {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    IdentificationCardTypeRepository identificationCardTypeRepository;

    @Autowired
    CustomerTypeRepository customerTypeRepository;

    public Long createCustomer(co.edu.javeriana.pica.kallsonys.dto.Customer customerDTO) throws KallSonysException {
        Optional<IdentificationCardType> identificationCardTypeOptional =
                identificationCardTypeRepository.findById(customerDTO.getIdentificationCardType());
        if (!identificationCardTypeOptional.isPresent()) {
            throw new KallSonysException("Tipo de Identificación inválido.");
        }
         if (customerRepository.findCustomerByIdentificationCardTypeAndIdentificationCard(
                 customerDTO.getIdentificationCardType(),
                 customerDTO.getIdentificationCard()) != null) {
            throw new KallSonysException("Ya existe un Cliente con el Tipo y Número de Identificación proporcionados.");
         }

         if (customerRepository.findCustomerByEmail(customerDTO.getEmail())!= null) {
             throw new KallSonysException("Ya existe un Cliente con el Email proporcionado.");
         }

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setIdentificationCardType(identificationCardTypeOptional.get());
        customer.setIdentificationCard(customerDTO.getIdentificationCard());
        customer.setType(new CustomerType());
        customer.getType().setId(CustomerTypeEnum.PLATINO.getId());

        return customerRepository.save(customer).getId();
    }

    public void updateCustomer(co.edu.javeriana.pica.kallsonys.dto.Customer customerDTO) throws KallSonysException {
        if (customerDTO.getId() == null) {
            throw new KallSonysException("No se especificó el id del Cliente.");
        }

        Optional<Customer> customerOptional = customerRepository.findById(customerDTO.getId());
        if (!customerOptional.isPresent()) {
            throw new KallSonysException("El Cliente no existe.");
        }

        Optional<IdentificationCardType> identificationCardTypeOptional =
                identificationCardTypeRepository.findById(customerDTO.getIdentificationCardType());
        if (!identificationCardTypeOptional.isPresent()) {
            throw new KallSonysException("Tipo de Identificación inválido.");
        }

        Customer customer = customerOptional.get();

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhoneNumber());
        customer.setIdentificationCardType(identificationCardTypeOptional.get());
        customer.setIdentificationCard(customerDTO.getIdentificationCard());

        customerRepository.save(customer);
    }

    public void updateType(Long idCustomer, Integer idCategoria) throws KallSonysException {
        if (idCustomer == null) {
            throw new KallSonysException("El Id del Cliente es requerido.");
        }
        if (idCategoria == null) {
            throw new KallSonysException("El Id de la Categoría es requerido.");
        }
        Optional<Customer> customerOptional = customerRepository.findById(idCustomer);
        if (!customerOptional.isPresent()) {
            throw new KallSonysException("El Cliente no existe.");
        }

        Optional<CustomerType> customerTypeOptional = customerTypeRepository.findById(idCategoria);
        if (!customerTypeOptional.isPresent()) {
            throw new KallSonysException("La Categoría no existe.");
        }

        Customer customer = customerOptional.get();
        customer.setType(customerTypeOptional.get());
        customerRepository.save(customer);
    }

    public co.edu.javeriana.pica.kallsonys.dto.Customer findById(Long id) throws KallSonysException {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new KallSonysException("El Cliente no existe.");
        }

        Customer customer = customerOptional.get();

        return customerEntityToCustomerDTO(customer);
    }

    public co.edu.javeriana.pica.kallsonys.dto.Customer findByIdentificationCardTypeAndIdentificationCard(
            String identificationCardType, String identificationCard) throws KallSonysException {
        Customer customer = customerRepository.findCustomerByIdentificationCardTypeAndIdentificationCard(
                identificationCardType,
                identificationCard);
        if (customer == null) {
            throw new KallSonysException("El Cliente no existe.");
        }
        return customerEntityToCustomerDTO(customer);
    }

    public GenericPage<co.edu.javeriana.pica.kallsonys.dto.Customer> findByProductId(
            Long productId, String ordering, int page, int results) throws KallSonysException {
        if (productId == null) {
            throw new KallSonysException("El código del producto es obligatorio.");
        }

        Sort sort;
        if (ordering == null || ordering.isEmpty() || ordering.toUpperCase().equals("ASC")
                || !ordering.toUpperCase().equals("DESC")) {
            sort = Sort.by(Arrays.asList(Sort.Order.asc("identificationCardType"), Sort.Order.asc("identificationCard")));
        } else {
            sort = Sort.by(Arrays.asList(Sort.Order.desc("identificationCardType"), Sort.Order.desc("identificationCard")));
        }
        Pageable sortedByIdentCardType = PageRequest.of(page, results, sort);

        List<co.edu.javeriana.pica.kallsonys.dto.Customer> customerDTOs = new ArrayList<>();
        Page<Customer> pageElements = customerRepository.findByProductId(productId, sortedByIdentCardType);
        for (Customer customer : pageElements.toList()) {
            customerDTOs.add(customerEntityToCustomerDTO(customer));
        }
        return new GenericPage(customerDTOs, pageElements.getTotalElements());
    }

    public co.edu.javeriana.pica.kallsonys.dto.Customer findByEmail(String email) throws KallSonysException {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new KallSonysException("El Cliente no existe.");
        }
        return customerEntityToCustomerDTO(customer);
    }

    public List<CustomerPayment> customersPaymentRankingBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Object[]> ranking = customerRepository.customersPaymentRankingBetweenDates(startDate, endDate);
        List<co.edu.javeriana.pica.kallsonys.dto.CustomerPayment> customerPaymentRanking = new ArrayList<>();
        for (Object[] reg : ranking) {
            co.edu.javeriana.pica.kallsonys.dto.CustomerPayment customerPayment =
                    new co.edu.javeriana.pica.kallsonys.dto.CustomerPayment();
            customerPayment.setCustomer(
                    customerEntityToCustomerDTO(
                            customerRepository.findById(((BigDecimal)reg[0]).longValue()).get()));
            customerPayment.setDate(((Timestamp)reg[1]).toLocalDateTime().toLocalDate());
            customerPayment.setPrice((BigDecimal)reg[2]);
            customerPaymentRanking.add(customerPayment);
        }
        return customerPaymentRanking;
    }

    private co.edu.javeriana.pica.kallsonys.dto.Customer customerEntityToCustomerDTO (Customer customer) {
        co.edu.javeriana.pica.kallsonys.dto.Customer customerDTO = new co.edu.javeriana.pica.kallsonys.dto.Customer();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhone());
        customerDTO.setIdentificationCardType(customer.getIdentificationCardType().getId());
        customerDTO.setIdentificationCard(customer.getIdentificationCard());
        customerDTO.setType(new co.edu.javeriana.pica.kallsonys.dto.Type());
        customerDTO.getType().setId(customer.getType().getId());
        customerDTO.getType().setName(customer.getType().getName());
        return customerDTO;
    }
}
