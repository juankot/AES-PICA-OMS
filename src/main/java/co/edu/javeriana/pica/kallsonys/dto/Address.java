package co.edu.javeriana.pica.kallsonys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Address {

    @NotNull(message = "La dirección es obligatoria")
    @NotBlank(message = "La dirección es obligatoria")
    @JsonProperty("Street")
    private String street;

    @NotNull(message = "El código ZIP es obligatorio")
    @NotBlank(message = "El código ZIP es obligatorio")
    @JsonProperty("Zip")
    private String zip;

    @NotNull(message = "La localidad es obligatoria")
    @NotBlank(message = "La localidad es obligatoria")
    @JsonProperty("State")
    private String state;

    @NotNull(message = "La ciudad es obligatoria")
    @NotBlank(message = "La ciudad es obligatoria")
    @JsonProperty("City")
    private String city;

    @NotNull(message = "El código del país es obligatorio")
    @NotBlank(message = "El código del país es obligatorio")
    @JsonProperty("CountryCode")
    private String countryCode;

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
