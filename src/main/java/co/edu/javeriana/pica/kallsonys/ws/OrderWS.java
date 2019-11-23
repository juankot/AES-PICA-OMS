package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.GenericPage;
import co.edu.javeriana.pica.kallsonys.dto.Order;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderWS extends GeneralWS {

    @Autowired
    OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Order order) throws KallSonysException {
        return ResponseEntity.ok(orderFacade.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) throws KallSonysException {
        return ResponseEntity.ok(orderFacade.findById(id));
    }

    @GetMapping("/findByProductCode/{productCode}")
    public ResponseEntity findOrdersByProductCode(
            @PathVariable("productCode") String productCode,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) throws KallSonysException {
        GenericPage<Order> genericPage = orderFacade.findOrdersByProductCode(productCode, ordering, page, results);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count", String.valueOf(genericPage.getTotalElements()));
        return ResponseEntity.ok().headers(responseHeaders).body(genericPage.getList());
    }

    @GetMapping("/monthlyReportByStatus/{year}/{month}/{statusId}")
    public ResponseEntity findMonthlyReportByYearMonthAndStatus(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("statusId") Integer statusId) throws KallSonysException {
        return ResponseEntity.ok(orderFacade.findMonthlyReportByMonthAndStatus(year, month, statusId));
    }

    @GetMapping("/findAllByStatus/{statusId}")
    public ResponseEntity findAllByStatusAndOrderedByDate(
            @PathVariable Integer statusId,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) {
        GenericPage<Order> genericPage = orderFacade.findAllByStatusAndOrderedByDate(statusId, ordering, page, results);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count", String.valueOf(genericPage.getTotalElements()));
        return ResponseEntity.ok().headers(responseHeaders).body(genericPage.getList());
    }

    @GetMapping("/paymentRanking/{statusId}")
    public ResponseEntity ordersPaymentRankingByStatusBetweenDates(
            @PathVariable Integer statusId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) {
        GenericPage<Order> genericPage =
                orderFacade.ordersPaymentRankingByStatusBetweenDates(statusId, start, end, ordering, page, results);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count", String.valueOf(genericPage.getTotalElements()));
        return ResponseEntity.ok().headers(responseHeaders).body(genericPage.getList());
    }

    @GetMapping("/findByCustomerId/{customerId}")
    public ResponseEntity findByCustomerId(
            @PathVariable Long customerId,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) {
        GenericPage<Order> genericPage = orderFacade.findByCustomerId(customerId, ordering, page, results);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count", String.valueOf(genericPage.getTotalElements()));
        return ResponseEntity.ok().headers(responseHeaders).body(genericPage.getList());
    }

}
