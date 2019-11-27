package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "item")
@Table(
        indexes = {
                @Index(name = "item_prod_id_idx", columnList = "product_id"),
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

    @Column(name = "product_id", nullable = false)
    private Long productId;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
