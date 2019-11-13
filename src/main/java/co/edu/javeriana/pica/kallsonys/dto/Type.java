package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.NotNull;

public class Type {

    @NotNull(message = "El id es obligatorio")
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
