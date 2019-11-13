package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Customer {

    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotNull(message = "El apellido es obligatorio")
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotNull(message = "El teléfono es obligatorio")
    @NotBlank(message = "El teléfono es obligatorio")
    private String phoneNumber;

    @NotNull(message = "El correo es obligatorio")
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String email;

    @NotNull(message = "El tipo de identificación es obligatorio")
    private String identificationCardType;

    @NotNull(message = "El número de identificación es obligatorio")
    @NotBlank(message = "El número de identificación es obligatorio")
    private String identificationCard;

    private Type type;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationCardType() {
        return identificationCardType;
    }

    public void setIdentificationCardType(String identificationCardType) {
        this.identificationCardType = identificationCardType;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
