package dev.naman.productservice.dtos;

import java.util.List;

public class SearchRequestDto {
    private String query;
    private int pageNumber;
    private int pageSize;
    private List<SortParameter> sortByParameters;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SortParameter> getSortByParameters() {
        return sortByParameters;
    }

    public void setSortByParameters(List<SortParameter> sortByParameters) {
        this.sortByParameters = sortByParameters;
    }
}
