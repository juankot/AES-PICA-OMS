package co.edu.javeriana.pica.kallsonys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRanking {

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Quantity")
    private Integer quantity;

    public ProductRanking(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
