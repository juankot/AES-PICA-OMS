package co.edu.javeriana.pica.kallsonys.facade;

import co.edu.javeriana.pica.kallsonys.business.OrderBusiness;
import co.edu.javeriana.pica.kallsonys.dto.MonthlyOrderReport;
import co.edu.javeriana.pica.kallsonys.dto.Order;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderFacade {

    @Autowired
    OrderBusiness orderBusiness;

    public Long createOrder(Order order) throws KallSonysException {
        return orderBusiness.createOrder(order);
    }

    public Order findById(Long id) throws KallSonysException {
        return orderBusiness.findById(id);
    }

    public List<Order> findOrdersByProductCode(String productCode) throws KallSonysException {
        return orderBusiness.findOrdersByProductCode(productCode);
    }

    public MonthlyOrderReport findMonthlyReportByMonthAndStatus(Integer year, Integer month, Integer statusId)
            throws KallSonysException {
        return orderBusiness.findMonthlyReportByMonthAndStatus(year, month, statusId);
    }

    public List<Order> findAllByStatusAndOrderedByDate(Integer statusId, String ordering, int page, int results) {
        return orderBusiness.findAllByStatusAndOrderedByDate(statusId, ordering, page, results);
    }

    public List<Order> ordersPaymentRankingByStatusBetweenDates(
            Integer statusId, Date startDate, Date endDate, String ordering, int page, int results) {
        return orderBusiness.ordersPaymentRankingByStatusBetweenDates(
                statusId, startDate, endDate, ordering, page, results);
    }
}
