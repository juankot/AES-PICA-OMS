package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Item {

    private Long id;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal price;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer quantity;

    @NotNull(message = "El código del producto es obligatorio")
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
