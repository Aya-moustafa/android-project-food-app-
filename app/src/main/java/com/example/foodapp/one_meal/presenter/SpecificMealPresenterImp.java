package com.example.foodapp.one_meal.presenter;

import android.util.Log;

import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepository;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.network.MealDetailsCallBack;
import com.example.foodapp.network.MealsCallBack;
import com.example.foodapp.one_meal.view.SpecificMealView;

import java.util.List;

public class SpecificMealPresenterImp implements MealDetailsCallBack, SpecificMealPresenter , MealsCallBack {
  SpecificMealView _view;
  CategoriesRepository _repo;

    public SpecificMealPresenterImp(SpecificMealView _view, CategoriesRepositoryImp _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    public SpecificMealPresenterImp(CategoriesRepository _repo) {
        this._repo = _repo;
    }

    @Override
    public MealDetails getMealByname(String mealName) {
        _repo.getMealByName(this , mealName);
        return null;
    }

    @Override
    public void addToFavMeals(BeefMealsData mealDetails) {
        _repo.insertMealsToFav(mealDetails);
    }

  @Override
  public void addMealToPlan(MealWithDate mealWithDate) {
     _repo.insertMealsPlan(mealWithDate) ;
  Log.i("TAG", "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<addMealToPlan>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: "+mealWithDate);
  }

  @Override
    public void onSuccessResultMealDetails(List<MealDetails> List) {
         _view.showMeal(List);
    }


    @Override
    public void onSuccessResultMeal(List<BeefMealsData> List) {
    }

    @Override
    public void onFailureResult(String errormsg) {

    }
}
