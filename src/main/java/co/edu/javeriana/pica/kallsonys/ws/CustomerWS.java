package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.Customer;
import co.edu.javeriana.pica.kallsonys.dto.GenericResponse;
import co.edu.javeriana.pica.kallsonys.dto.Type;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity modify(@PathVariable @NotBlank Long id, @RequestBody Customer customer) throws KallSonysException {
        customer.setId(id);
        customerFacade.updateCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/updateType/{id}")
    public ResponseEntity updateType(@PathVariable @NotBlank Long id, @Valid @RequestBody Type customerType) throws KallSonysException {
        customerFacade.updateType(id, customerType.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable @NotBlank Long id) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findById(id));
    }

    @GetMapping("/{identificationCardType}/{identificationCard}")
    public ResponseEntity findByIdentificationCardTypeAndIdentificationCard(
            @PathVariable @NotBlank String identificationCardType,
            @PathVariable @NotBlank String identificationCard) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findByIdentificationCardTypeAndIdentificationCard(
                identificationCardType,
                identificationCard));
    }

    @GetMapping("/findByProductCode/{productCode}")
    public ResponseEntity findByProductCode(
            @PathVariable @NotBlank String productCode) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findByProductCode(productCode));
    }

    @GetMapping("/paymentRanking")
    public ResponseEntity customersPaymentRankingBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        return ResponseEntity.ok(customerFacade.customersPaymentRankingBetweenDates(start, end));
    }



}
