package com.example.foodapp.model.all_category.beef_meals;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "fav_meals")
public class BeefMealsData implements Serializable {
    @PrimaryKey
    @NonNull
    private String strMeal;
    @NonNull
    private String strMealThumb;
    @NonNull
    private String idMeal;
    private String userEmail;

    public BeefMealsData () {
    }

    public BeefMealsData(String strMeal, String strMealThumb, String idMeal,String userEmail) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
        this.userEmail = userEmail;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
