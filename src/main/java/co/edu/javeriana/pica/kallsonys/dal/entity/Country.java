package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "country")
public class Country {

    @Id
    @Column(length = 4)
    private String code;

    @Column(nullable = false, length = 30)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
