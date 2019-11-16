package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {

    private Long id;

    @NotNull(message = "El nombre del cliente es obligatorio")
    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 40, message = "El nombre del cliente debe tener máximo 40 caracteres")
    private String firstName;

    @NotNull(message = "El apellido del cliente es obligatorio")
    @NotBlank(message = "El apellido del cliente es obligatorio")
    @Size(max = 40, message = "El apellido del cliente debe tener máximo 40 caracteres")
    private String lastName;

    @NotNull(message = "El teléfono del cliente es obligatorio")
    @NotBlank(message = "El teléfono del cliente es obligatorio")
    @Size(max = 40, message = "El teléfono del cliente debe tener máximo 40 caracteres")
    private String phoneNumber;

    @NotNull(message = "El correo del cliente es obligatorio")
    @NotBlank(message = "El correo del cliente es obligatorio")
    @Email(message = "El formato del correo del cliente no es válido")
    @Size(max = 40, message = "El correo del cliente debe tener máximo 40 caracteres")
    private String email;

    @NotNull(message = "El código del tipo de identificación del cliente es obligatorio")
    @Size(max = 3, message = "El código del tipo de identificación del cliente debe tener máximo 3 caracteres")
    private String identificationCardType;

    @NotNull(message = "El número de identificación del cliente es obligatorio")
    @NotBlank(message = "El número de identificación del cliente es obligatorio")
    @Size(max = 40, message = "El cnúmero de identificación del cliente debe tener máximo 40 caracteres")
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
