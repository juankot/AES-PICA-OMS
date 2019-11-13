package co.edu.javeriana.pica.kallsonys.enums;

public enum CustomerTypeEnum {
    PLATEADO(1),
    DORADO(2),
    PLATINO(3);

    private final Integer id;

    CustomerTypeEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
