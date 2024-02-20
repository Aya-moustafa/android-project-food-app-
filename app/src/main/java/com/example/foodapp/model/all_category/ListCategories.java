package com.example.foodapp.model.all_category;

import java.util.List;

public class ListCategories {
    public List<AllCategory> categories;
    public ListCategories() {

    }

    public ListCategories(List<AllCategory> categories) {
        this.categories = categories;
    }

    public List<AllCategory> getProducts() {
        return categories;
    }

    public void setProductsList(List<AllCategory> categories) {
        this.categories = categories;
    }
}
