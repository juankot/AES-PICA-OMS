package co.edu.javeriana.pica.kallsonys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MonthlyOrderReport {

    @JsonProperty("Quantity")
    private Integer quantity;

    @JsonProperty("Total")
    private BigDecimal total;

    public MonthlyOrderReport() {
        this.quantity = 0;
        this.total = BigDecimal.ZERO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
