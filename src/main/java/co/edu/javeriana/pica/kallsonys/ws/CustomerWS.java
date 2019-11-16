package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.Customer;
import co.edu.javeriana.pica.kallsonys.dto.Type;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RestController
@RequestMapping("/customer")
public class CustomerWS extends GeneralWS {

    @Autowired
    CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Customer customer) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.createCustomer(customer));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity modify(@PathVariable("id") @NotBlank Long id, @RequestBody Customer customer) throws KallSonysException {
        customer.setId(id);
        customerFacade.updateCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/updateType/{id}")
    public ResponseEntity updateType(@PathVariable("id") @NotBlank Long id, @Valid @RequestBody Type customerType) throws KallSonysException {
        customerFacade.updateType(id, customerType.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") @NotBlank Long id) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findById(id));
    }

    @GetMapping("/{identificationCardType}/{identificationCard}")
    public ResponseEntity findByIdentificationCardTypeAndIdentificationCard(
            @PathVariable("identificationCardType") @NotBlank String identificationCardType,
            @PathVariable("identificationCard") @NotBlank String identificationCard) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findByIdentificationCardTypeAndIdentificationCard(
                identificationCardType,
                identificationCard));
    }

    @GetMapping("/findByProductCode/{productCode}")
    public ResponseEntity findByProductCode(
            @PathVariable("productCode") @NotBlank String productCode,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findByProductCode(productCode, ordering, page, results));
    }

    @GetMapping("/paymentRanking")
    public ResponseEntity customersPaymentRankingBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return ResponseEntity.ok(customerFacade.customersPaymentRankingBetweenDates(start, end));
    }



}
