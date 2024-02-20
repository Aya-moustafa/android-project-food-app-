package com.example.foodapp.model.Ingrediants;

public class IngrediantsData {
    private String idIngredient;
    private String strDescription;
    private String strIngredient;
    private String strType;

    public IngrediantsData(String idIngredient, String strDescription, String strIngredient, String strType) {
        this.idIngredient = idIngredient;
        this.strDescription = strDescription;
        this.strIngredient = strIngredient;
        this.strType = strType;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrType() {
        return strType;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }
}
