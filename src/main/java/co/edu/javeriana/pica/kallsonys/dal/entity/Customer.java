package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity(name = "customer")
@Table(
        indexes = {
                @Index(name = "cust_ident_card_idx", columnList = "ident_card_type_id, identification_card"),
                @Index(name = "cust_email_idx", columnList = "email")
        }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "customer_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(nullable = false, length = 40)
    private String phone;

    @Email
    @Column(nullable = false, length = 40)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "ident_card_type_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "cust_ident_card_type_fk"))
    private IdentificationCardType identificationCardType;

    @Column(name = "identification_card", nullable = false, length = 40)
    private String identificationCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "type_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_customer_type_fk"))
    private CustomerType type;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IdentificationCardType getIdentificationCardType() {
        return identificationCardType;
    }

    public void setIdentificationCardType(IdentificationCardType identificationCardType) {
        this.identificationCardType = identificationCardType;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
