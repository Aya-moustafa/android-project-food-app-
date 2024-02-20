package com.example.foodapp.model.MealPlan;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "scheduled_meals")
public class MealWithDate implements Serializable {
    @PrimaryKey
    @NonNull
    private String strMeal;
    @NonNull
    private String strMealThumb;
    @NonNull
    private String idMeal;
    private String mealDate;


    public MealWithDate() {

    }

    public MealWithDate(@NonNull String strMeal, @NonNull String strMealThumb, @NonNull String idMeal, String mealDate) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
        this.mealDate = mealDate;
    }

    public void setStrMeal(@NonNull String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrMealThumb(@NonNull String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    @NonNull
    public String getStrMeal() {
        return strMeal;
    }

    @NonNull
    public String getStrMealThumb() {
        return strMealThumb;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public String getMealDate() {
        return mealDate;
    }
}
