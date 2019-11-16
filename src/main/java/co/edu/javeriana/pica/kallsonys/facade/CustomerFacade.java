package co.edu.javeriana.pica.kallsonys.facade;

import co.edu.javeriana.pica.kallsonys.business.CustomerBusiness;
import co.edu.javeriana.pica.kallsonys.dto.Customer;
import co.edu.javeriana.pica.kallsonys.dto.CustomerPayment;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CustomerFacade {

    @Autowired
    CustomerBusiness customerBusiness;

    public Long createCustomer(Customer customer) throws KallSonysException {
        return customerBusiness.createCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws KallSonysException {
        customerBusiness.updateCustomer(customer);
    }

    public void updateType(Long idCustomer, Integer idCategoria) throws KallSonysException {
        customerBusiness.updateType(idCustomer, idCategoria);
    }

    public Customer findById(Long id) throws KallSonysException {
        return customerBusiness.findById(id);
    }

    public Customer findByIdentificationCardTypeAndIdentificationCard(
            String identificationCardType, String identificationCard) throws KallSonysException {
        return customerBusiness.findByIdentificationCardTypeAndIdentificationCard(
                identificationCardType,
                identificationCard);
    }

    public List<Customer> findByProductCode(String productCode, String ordering, int page, int results) throws KallSonysException {
        return customerBusiness.findByProductCode(productCode, ordering, page, results);
    }

    public List<CustomerPayment> customersPaymentRankingBetweenDates(LocalDate startDate, LocalDate endDate) {
        return customerBusiness.customersPaymentRankingBetweenDates(startDate, endDate);
    }

}
