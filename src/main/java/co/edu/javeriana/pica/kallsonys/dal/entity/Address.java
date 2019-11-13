package co.edu.javeriana.pica.kallsonys.dal.entity;


import javax.persistence.*;

@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    @SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "address_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 150)
    private String street;

    @Column(nullable = false, length = 10)
    private String zip;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false, length = 50)
    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "country_code",
            nullable = false,
            referencedColumnName = "code",
            foreignKey = @ForeignKey(name = "address_country_code_fk"))
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
