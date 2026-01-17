package dev.naman.productservice.dtos;

public class SortParameter {
    private String parameterName;
    //"ASC" for ascending "DESC" for descending
    private String sortType;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
