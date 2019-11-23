package co.edu.javeriana.pica.kallsonys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Type {

    @NotNull(message = "El id del tipo es obligatorio")
    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("Name")
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
