package co.edu.javeriana.pica.kallsonys.dal.repository;

import co.edu.javeriana.pica.kallsonys.dal.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(
            "select c from customer c where c.identificationCardType.id = :identificationCardType and c.identificationCard = :identificationCard")
    Customer findCustomerByIdentificationCardTypeAndIdentificationCard(
            @Param("identificationCardType") String identificationCardType,
            @Param("identificationCard") String identificationCard);

    @Query(value = "SELECT DISTINCT C.* from ITEM I " +
            "INNER JOIN KS_ORDER O ON I.ORDER_ID = O.ID " +
            "INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID " +
            "WHERE I.PRODUCT_CODE = :productCode",
            nativeQuery = true)
    List<Customer> findByProductCode(@Param("productCode")  String productCode);

    @Query(value = "SELECT c.ID, TO_DATE(TO_CHAR(o.ORDER_DATE, 'dd-MM-yyyy')), sum(o.PRICE) AS PRICE FROM KS_ORDER O " +
            "INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID " +
            "WHERE TO_CHAR(O.ORDER_DATE, 'dd-MM-yyyy') BETWEEN :startDate AND :endDate " +
            "GROUP BY c.ID, TO_DATE(TO_CHAR(o.ORDER_DATE, 'dd-MM-yyyy')) " +
            "ORDER BY PRICE DESC",
            nativeQuery = true)
    List<Object[]> customersPaymentRankingBetweenDates(
            @Param("startDate") Date startDate,
            @Param("endDate")Date endDate);

    Customer findCustomerByEmail(String email);

}
