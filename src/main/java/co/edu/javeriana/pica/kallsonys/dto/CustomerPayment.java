package co.edu.javeriana.pica.kallsonys.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerPayment {

    private Customer customer;
    private Date date;
    private BigDecimal price;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
