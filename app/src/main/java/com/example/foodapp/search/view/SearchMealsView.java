package com.example.foodapp.search.view;

import com.example.foodapp.home_page.get_all_categories.view.AllCategoriesView;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.network.HomeCallBack;

import java.util.List;

public interface SearchMealsView {
    public void showResultSearch(List<MealDetails> list);
    public void showErrorMsg () ;

}
