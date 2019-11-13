package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.GenericResponse;
import co.edu.javeriana.pica.kallsonys.dto.MonthlyOrderReport;
import co.edu.javeriana.pica.kallsonys.dto.Order;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/monthlyReport/{year}/{month}/{statusId}")
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
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) {
        return ResponseEntity.ok(
                orderFacade.ordersPaymentRankingByStatusBetweenDates(
                        statusId, start, end, ordering, page, results));
    }

}
