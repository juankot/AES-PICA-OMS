package co.edu.javeriana.pica.kallsonys.dal.repository;

import co.edu.javeriana.pica.kallsonys.dal.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    @Query(value = "select i.order from item i where i.productCode = :productCode")
    List<Order> findOrdersByProductCode(@Param("productCode") String productCode);

    @Query(value =
                "SELECT COUNT(O.STATUS_ID) AS QUANTITY, SUM(O.PRICE) AS TOTAL " +
                "FROM KS_ORDER O " +
                "WHERE O.STATUS_ID = :statusId " +
                "AND TO_CHAR(O.ORDER_DATE, 'MM-yyyy') = :monthYear " +
                "GROUP BY O.STATUS_ID",
            nativeQuery = true)
    List<Object[]> findMonthlyReportByMonthAndStatus(@Param("monthYear") String monthYear, @Param("statusId") Integer statusId);

    List<Order> findAllByStatusId(Integer statusId, Pageable pageable);

    List<Order> findByStatusIdAndDateBetween(Integer statusId, Date start, Date end, Pageable pageable);
}
