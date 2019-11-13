package co.edu.javeriana.pica.kallsonys.dal.entity;

import javax.persistence.*;

@Entity(name = "identification_card_type")
public class IdentificationCardType {

    @Id
    @Column(length = 3)
    private String id;

    @Column(nullable = false, length = 30)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
