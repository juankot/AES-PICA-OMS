package co.edu.javeriana.pica.kallsonys.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerPayment {

    private Customer customer;
    private LocalDate date;
    private BigDecimal price;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
