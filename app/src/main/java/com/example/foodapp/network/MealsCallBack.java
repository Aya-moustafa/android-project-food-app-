package com.example.foodapp.network;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

public interface MealsCallBack {
    public void onSuccessResultMeal(List<BeefMealsData> List) ;
    public void onFailureResult(String errormsg);
}
