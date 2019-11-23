package co.edu.javeriana.pica.kallsonys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Comments")
    private String comments;

    @NotNull(message = "La dirección de la orden es obligatoria")
    @JsonProperty("Address")
    private Address address;

    @NotNull(message = "El id del cliente de la orden es obligatorio")
    @JsonProperty("IdCustomer")
    private Long idCustomer;

    @JsonProperty("Price")
    private BigDecimal price;

    @NotNull(message = "Por lo menos un ítem de la orden es obligatorio")
    @JsonProperty("Items")
    private List<Item> items;

    @JsonProperty("Status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
