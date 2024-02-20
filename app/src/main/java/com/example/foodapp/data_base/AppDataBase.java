package com.example.foodapp.data_base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

@Database(entities = {BeefMealsData.class, MealWithDate.class},version = 4)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract MealsLocalDataSource getMealsDao();
    public abstract MealsPlanLocalDataSource getMealsPlanDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"mealsdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
