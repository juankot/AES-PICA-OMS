package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Address {

    private Long id;

    @NotNull(message = "La dirección es obligatoria")
    @NotBlank(message = "La dirección es obligatoria")
    private String street;

    @NotNull(message = "El código ZIP es obligatorio")
    @NotBlank(message = "El código ZIP es obligatorio")
    private String zip;

    @NotNull(message = "La localidad es obligatoria")
    @NotBlank(message = "La localidad es obligatoria")
    private String state;

    @NotNull(message = "La ciudad es obligatoria")
    @NotBlank(message = "La ciudad es obligatoria")
    private String city;

    @NotNull(message = "El código del país es obligatorio")
    @NotBlank(message = "El código del país es obligatorio")
    private String countryCode;

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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
