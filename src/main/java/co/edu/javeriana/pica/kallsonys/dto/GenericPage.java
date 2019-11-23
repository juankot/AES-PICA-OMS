package co.edu.javeriana.pica.kallsonys.dto;

import java.util.List;

public class GenericPage<T> {

    private List<T> list;
    private long totalElements;

    public GenericPage(List<T> list, long totalElements) {
        this.list = list;
        this.totalElements = totalElements;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
