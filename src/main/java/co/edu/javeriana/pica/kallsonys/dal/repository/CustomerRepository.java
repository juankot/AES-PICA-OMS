package co.edu.javeriana.pica.kallsonys.dal.repository;

import co.edu.javeriana.pica.kallsonys.dal.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    @Query(
            "select c from customer c where c.identificationCardType.id = :identificationCardType and c.identificationCard = :identificationCard")
    Customer findCustomerByIdentificationCardTypeAndIdentificationCard(
            @Param("identificationCardType") String identificationCardType,
            @Param("identificationCard") String identificationCard);

    @Query("select distinct C from customer C " +
            "inner join ks_order O on C.id = O.customer.id " +
            "inner join item I on I.order.id = O.id " +
            "where I.productId = :productId")
    Page<Customer> findByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query(value = "SELECT c.ID, o.ORDER_DATE, sum(o.PRICE) AS PRICE FROM KS_ORDER O " +
            "INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID " +
            "WHERE O.ORDER_DATE BETWEEN :startDate AND :endDate " +
            "GROUP BY c.ID, o.ORDER_DATE " +
            "ORDER BY PRICE DESC",
            nativeQuery = true)
    List<Object[]> customersPaymentRankingBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate")LocalDate endDate);

    Customer findCustomerByEmail(String email);

}
