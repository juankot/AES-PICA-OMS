package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.*;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import co.edu.javeriana.pica.kallsonys.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerWS extends GeneralWS {

    @Autowired
    CustomerFacade customerFacade;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Customer customer) throws KallSonysException, Exception {
//        Properties wsProps = new Properties();
//        wsProps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "services.properties"));
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        User user = new User();
//        user.setEmail(customer.getEmail());
//        user.setPassword(customer.getPassword());
//        HttpEntity<User> entityUser = new HttpEntity<User>(user, httpHeaders);
//        try {
//            UserResponse userResponse = restTemplate.exchange(wsProps.getProperty("ws.brokered_auth"), HttpMethod.POST, entityUser, UserResponse.class).getBody();
//            if (userResponse.isResult()) {
                return ResponseEntity.ok(new CreateResponse(customerFacade.createCustomer(customer)));
//            } else {
//                throw new Exception(userResponse.getMessage());
//            }
//        } catch (HttpClientErrorException e) {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode actualObj = mapper.readTree(e.getResponseBodyAsString());
//            throw new Exception(actualObj.get("message").textValue());
//        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity modify(@PathVariable("id") @NotBlank Long id, @RequestBody Customer customer) throws KallSonysException {
        customer.setId(id);
        customerFacade.updateCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateType/{id}")
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

    @GetMapping("/findByProductId/{productId}")
    public ResponseEntity findByProductCode(
            @PathVariable("productId") @NotBlank Long productId,
            @RequestParam String ordering,
            @RequestParam int page,
            @RequestParam int results) throws KallSonysException {
        GenericPage<Customer> genericPage = customerFacade.findByProductId(productId, ordering, page, results);
        return ResponseEntity.ok(genericPage);
    }

    @GetMapping("/paymentRanking")
    public ResponseEntity customersPaymentRankingBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return ResponseEntity.ok(customerFacade.customersPaymentRankingBetweenDates(start, end));
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity findById(@PathVariable("email") @NotBlank String email) throws KallSonysException {
        return ResponseEntity.ok(customerFacade.findByEmail(email));
    }



}
