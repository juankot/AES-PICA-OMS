package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.Order;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
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
    public ResponseEntity findOrdersByProductCode(@PathVariable("productCode") String productCode) throws KallSonysException {
        return ResponseEntity.ok(orderFacade.findOrdersByProductCode(productCode));
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
        return ResponseEntity.ok(orderFacade.findAllByStatusAndOrderedByDate(statusId, ordering, page, results));
    }

    @GetMapping("/paymentRanking/{statusId}")
    public ResponseEntity ordersPaymentRankingByStatusBetweenDates(
            @PathVariable Integer statusId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) {
        return ResponseEntity.ok(
                orderFacade.ordersPaymentRankingByStatusBetweenDates(
                        statusId, start, end, ordering, page, results));
    }

}
