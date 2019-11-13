package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "customer_type")
public class CustomerType {

    @Id
    @Column(precision = 2)
    private Integer id;

    @Column(length = 10)
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
