package com.example.foodapp.model.countries;

import java.util.List;

public class ListOfCountries {
    public List<AllCountries> meals;

    public ListOfCountries() {

    }

    public ListOfCountries(List<AllCountries> meals) {
        this.meals = meals;
    }

    public void setMeals(List<AllCountries> meals) {
        this.meals = meals;
    }

    public List<AllCountries> getMeals() {
        return meals;
    }
}
