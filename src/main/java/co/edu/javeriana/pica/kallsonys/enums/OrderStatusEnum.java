package co.edu.javeriana.pica.kallsonys.enums;

public enum OrderStatusEnum {

    EN_VALIDACION(1),
    EN_TRANSITO(2),
    RECHAZADA(3),
    CERRADA(4),
    CANCELADA(5);

    private final Integer id;

    OrderStatusEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
