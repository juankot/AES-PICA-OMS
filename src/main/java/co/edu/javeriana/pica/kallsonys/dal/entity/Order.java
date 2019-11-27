package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "ks_order")
@Table(
        indexes = {
                @Index(name = "order_cust_idx", columnList = "customer_id"),
                @Index(name = "order_status_idx", columnList = "status_id")
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "order_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    private String comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "address_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "order_address_fk"))
    private Address address;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "order_customer_fk"))
    private Customer customer;

    @Column(precision = 21, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ks_order_status_fk"))
    private Status status;

    @Column(name = "status_date")
    private LocalDate statusDate;

    @ManyToOne
    @JoinColumn(
            name = "inventory_prov_id",
            nullable = true,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ks_order_inv_prov_fk"))
    private InventoryProvider inventoryProvider;

    @ManyToOne
    @JoinColumn(
            name = "courier_prov_id",
            nullable = true,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ks_order_cour_prov_fk"))
    private CourierProvider courierProvider;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public InventoryProvider getInventoryProvider() {
        return inventoryProvider;
    }

    public void setInventoryProvider(InventoryProvider inventoryProvider) {
        this.inventoryProvider = inventoryProvider;
    }

    public CourierProvider getCourierProvider() {
        return courierProvider;
    }

    public void setCourierProvider(CourierProvider courierProvider) {
        this.courierProvider = courierProvider;
    }
}
