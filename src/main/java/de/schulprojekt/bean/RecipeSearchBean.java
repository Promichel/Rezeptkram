package de.schulprojekt.bean;

public class RecipeSearchBean {

    private int first;
    private int pageSize;



    public RecipeSearchBean() {
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
