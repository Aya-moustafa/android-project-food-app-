package com.example.foodapp.search.model;

import java.io.Serializable;
import java.util.List;

public class ListOfFilters implements Serializable {
    List<String> allFilters ;

    public ListOfFilters(List<String> allFilters) {
        this.allFilters = allFilters;
    }

    public List<String> getAllFilters() {
        return allFilters;
    }

    public void setAllFilters(List<String> allFilters) {
        this.allFilters = allFilters;
    }
}
