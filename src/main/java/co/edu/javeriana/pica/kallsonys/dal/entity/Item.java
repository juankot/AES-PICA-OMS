package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "item")
@Table(
        indexes = {
                @Index(name = "item_prod_code_idx", columnList = "product_code"),
                @Index(name = "item_order_idx", columnList = "order_id")
        }
)
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    @SequenceGenerator(name = "ITEM_SEQ", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 5)
    private Integer quantity;

    @Column(name = "product_code", nullable = false, length = 22)
    private String productCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_order_fk"))
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
