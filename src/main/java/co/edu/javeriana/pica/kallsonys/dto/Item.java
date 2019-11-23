package co.edu.javeriana.pica.kallsonys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Item {

    @NotNull(message = "El precio del ítem es obligatorio")
    @JsonProperty("Price")
    private BigDecimal price;

    @NotNull(message = "La cantidad del ítem es obligatoria")
    @JsonProperty("Quantity")
    private Integer quantity;

    @NotNull(message = "El código de producto del ítem es obligatorio")
    @JsonProperty("ProductCode")
    private String productCode;

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
