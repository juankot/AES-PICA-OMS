package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.*;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderWS extends GeneralWS {

    @Autowired
    OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Order order) throws KallSonysException {
        return ResponseEntity.ok(new CreateResponse(orderFacade.createOrder(order)));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) throws KallSonysException {
        return ResponseEntity.ok(orderFacade.findById(id));
    }

    @GetMapping("/findByProductId/{productId}")
    public ResponseEntity findOrdersByProductId(
            @PathVariable("productId") Long productId,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) throws KallSonysException {
        GenericPage<Order> genericPage = orderFacade.findOrdersByProductId(productId, ordering, page, results);
        return ResponseEntity.ok(genericPage);
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
        return ResponseEntity.ok(genericPage);
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
        return ResponseEntity.ok(genericPage);
    }

    @GetMapping("/findByCustomerId/{customerId}")
    public ResponseEntity findByCustomerId(
            @PathVariable Long customerId,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) {
        GenericPage<Order> genericPage = orderFacade.findByCustomerId(customerId, ordering, page, results);
        return ResponseEntity.ok(genericPage);
    }

    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity updateStatus(
            @PathVariable("id") @NotBlank Long id,
            @Valid @RequestBody Status orderStatus) throws KallSonysException {
        orderFacade.updateStatus(id, orderStatus.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/setInventoryProvider/{id}")
    public ResponseEntity setInventoryProvider(
            @PathVariable("id") @NotBlank Long id,
            @Valid @RequestBody Provider provider) throws KallSonysException {
        orderFacade.setInventoryProvider(id, provider.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/setCourierProvider/{id}")
    public ResponseEntity setCourierProvider(
            @PathVariable("id") @NotBlank Long id,
            @Valid @RequestBody Provider provider) throws KallSonysException, Exception {
        orderFacade.setCourierProvider(id, provider.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/sellingProductRanking")
    public ResponseEntity sellingProductRanking(
            @RequestParam @NotBlank @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @NotBlank @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return ResponseEntity.ok(orderFacade.sellingProductRanking(startDate, endDate));
    }

}
