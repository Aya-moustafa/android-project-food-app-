package com.example.foodapp.data_base;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataSourceImp implements MealsLocalDataSource{

    private final static String TAG ="MealsLocalDataSourceImp";
    private MealsLocalDataSource dao;
    private static MealsLocalDataSourceImp localsource = null;


    private Flowable<List<BeefMealsData>> listBeefMeals;

    public MealsLocalDataSourceImp(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealsDao();
        listBeefMeals = dao.getFavMeals();
    }

    public static MealsLocalDataSourceImp getInstance(Context context){
        if(localsource == null){
           localsource = new MealsLocalDataSourceImp(context);
        }
        return localsource;
    }
    @Override
    public Flowable<List<BeefMealsData>> getFavMeals() {
        return listBeefMeals;
    }

    @Override
    public void insertMeals(BeefMealsData beefMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertMeals(beefMeal);
            }
        }
        ).start();
    }

    @Override
    public void deleteMeals(BeefMealsData beefMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMeals(beefMeal);
            }
        }
        ).start();
    }



}
