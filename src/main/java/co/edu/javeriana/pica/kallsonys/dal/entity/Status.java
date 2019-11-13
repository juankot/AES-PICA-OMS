package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.*;

@Entity(name = "status")
public class Status {

    @Id
    @Column(precision = 2)
    private Integer id;

    @Column(nullable = false, length = 20)
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
