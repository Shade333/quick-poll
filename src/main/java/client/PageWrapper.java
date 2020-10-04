package client;

import java.util.List;

import org.springframework.data.domain.Sort;

public class PageWrapper<T> {

    private List<T> content;

    private boolean last;
    private boolean first;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private int nomberOfElements;
    private Sort sort;
    public List<T> getContent() {
        return content;
    }
    public void setContent(List<T> content) {
        this.content = content;
    }
    public boolean isLast() {
        return last;
    }
    public void setLast(boolean last) {
        this.last = last;
    }
    public boolean isFirst() {
        return first;
    }
    public void setFirst(boolean first) {
        this.first = first;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNomberOfElements() {
        return nomberOfElements;
    }
    public void setNomberOfElements(int nomberOfElements) {
        this.nomberOfElements = nomberOfElements;
    }
    public Sort getSort() {
        return sort;
    }
    public void setSort(Sort sort) {
        this.sort = sort;
    }

}