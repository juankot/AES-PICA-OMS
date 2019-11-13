package co.edu.javeriana.pica.kallsonys.dto;

import java.math.BigDecimal;

public class MonthlyOrderReport {

    private Integer quantity;
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
