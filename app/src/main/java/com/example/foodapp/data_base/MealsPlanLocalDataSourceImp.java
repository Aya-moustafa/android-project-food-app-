package com.example.foodapp.data_base;

import android.content.Context;
import android.util.Log;

import com.example.foodapp.model.MealPlan.MealWithDate;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsPlanLocalDataSourceImp implements MealsPlanLocalDataSource{
    private final static String TAG ="MealsPlanLocalDataSourceImp";
    private MealsPlanLocalDataSource daoMealPlan;
    private static MealsPlanLocalDataSourceImp planlocalsource = null;

    private Flowable<List<MealWithDate>> mealsPlan;

    public MealsPlanLocalDataSourceImp(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        daoMealPlan = db.getMealsPlanDao();
        mealsPlan = daoMealPlan.getMealsPlan();
    }

    public static MealsPlanLocalDataSourceImp getInstance(Context context){
        if(planlocalsource == null){
            planlocalsource = new MealsPlanLocalDataSourceImp(context);
        }
        return planlocalsource;
    }

    @Override
    public Flowable<List<MealWithDate>> getMealsPlan() {
        return mealsPlan;
    }

    @Override
    public Completable insertMealsinPlan(MealWithDate mealPlan) {
                Log.i(TAG, "Inserting meal plan into the database: " + mealPlan.toString());
              return   daoMealPlan.insertMealsinPlan(mealPlan);
    }

    @Override
    public Completable deleteMealsFromPlan(MealWithDate mealPlan) {
             return   daoMealPlan.deleteMealsFromPlan(mealPlan);
    }
}
