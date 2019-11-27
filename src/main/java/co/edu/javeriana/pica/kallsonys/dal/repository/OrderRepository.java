package co.edu.javeriana.pica.kallsonys.dal.repository;

import co.edu.javeriana.pica.kallsonys.dal.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    @Query(value = "select i.order from item i where i.productId = :productId")
    Page<Order> findOrdersByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query(value =
                "SELECT COUNT(O.STATUS_ID) AS QUANTITY, SUM(O.PRICE) AS TOTAL " +
                "FROM KS_ORDER O " +
                "WHERE O.STATUS_ID = :statusId " +
                "AND TO_CHAR(O.ORDER_DATE, 'MM-yyyy') = :monthYear " +
                "GROUP BY O.STATUS_ID",
            nativeQuery = true)
    List<Object[]> findMonthlyReportByMonthAndStatus(@Param("monthYear") String monthYear, @Param("statusId") Integer statusId);

    Page<Order> findAllByStatusId(Integer statusId, Pageable pageable);

    Page<Order> findByStatusIdAndDateBetween(Integer statusId, LocalDate start, LocalDate end, Pageable pageable);

    @Query(value = "SELECT O FROM ks_order O WHERE customer.id = :customerId")
    Page<Order> findAllByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

    @Query(value =
                "select i.PRODUCT_ID, sum(i.QUANTITY) as quantity " +
                "from ITEM i inner join KS_ORDER o on i.ORDER_ID = o.ID " +
                "where o.STATUS_ID in (2,4) " +
                "and o.STATUS_DATE between :startDate and :endDate " +
                "group by i.PRODUCT_ID " +
                "order by quantity desc",
            nativeQuery = true)
    List<Object[]> sellingProductRanking(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
