package com.example.foodapp.data_base;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;



import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsLocalDataSource {
    @Query("SELECT * From fav_meals")
    Flowable<List<BeefMealsData>> getFavMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeals (BeefMealsData beefMeal);
    @Delete
    void deleteMeals(BeefMealsData beefMeal);


}
