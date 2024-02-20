package com.example.foodapp.model.all_category;

import java.util.List;

public class AllCategory {

    private int idCategory;

    private String strCategory;

    private String strCategoryThumb;

    private List<AllCategory> listCategories;

    public  AllCategory () {

    }
    public AllCategory(int idCategory, String strCategory, String strCategoryThumb) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public List<AllCategory> getListCategories() {
        return listCategories;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }
}
