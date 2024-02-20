package com.example.foodapp.weekPlan.presenter;

import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.repository.CategoriesRepository;
import com.example.foodapp.weekPlan.view.WeekPlanView;

public class WeekPlanPresenterImp implements WeekPlanPresenter {
  WeekPlanView _view;
  CategoriesRepository _repo;

    public WeekPlanPresenterImp(WeekPlanView _view, CategoriesRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getStoredWeekMeals(){
        _view.showAllWeekPlanMeals(_repo.getStoredMealsPlan());
    }

    @Override
    public void deleteMealsFromWeekPlan(MealWithDate mealWithDate){
        _repo.deleteMealsPlan(mealWithDate);
    }

}
