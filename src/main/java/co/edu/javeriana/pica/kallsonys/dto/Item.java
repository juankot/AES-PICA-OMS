package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Item {

    private Long id;

    @NotNull(message = "El precio del ítem es obligatorio")
    private BigDecimal price;

    @NotNull(message = "La cantidad del ítem es obligatoria")
    private Integer quantity;

    @NotNull(message = "El código de producto del ítem es obligatorio")
    private String productCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
