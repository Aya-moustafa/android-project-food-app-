package com.example.foodapp.data_base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.foodapp.model.MealPlan.MealWithDate;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsPlanLocalDataSource {
    @Query("SELECT * From scheduled_meals")
    Flowable<List<MealWithDate>> getMealsPlan();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMealsinPlan (MealWithDate mealPlan);
    @Delete
    Completable deleteMealsFromPlan(MealWithDate mealPlan);
}
