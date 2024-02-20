package com.example.foodapp.weekPlan.view;

import com.example.foodapp.model.MealPlan.MealWithDate;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface WeekPlanView {
    public void showAllWeekPlanMeals (Flowable<List<MealWithDate>> weekPlanMeals);
}
