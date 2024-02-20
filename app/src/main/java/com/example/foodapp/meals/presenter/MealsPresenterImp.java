package com.example.foodapp.meals.presenter;
import android.util.Log;

import com.example.foodapp.meals.view.BeefMealsView;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepository;
import com.example.foodapp.network.MealsCallBack;

import java.util.List;

public class MealsPresenterImp implements MealsPresenter, MealsCallBack {

    private static final String TAG = "MealsPresenterImp" ;

    BeefMealsView _view;
    CategoriesRepository _repo;

    public MealsPresenterImp() {

    }
    public MealsPresenterImp(BeefMealsView _view, CategoriesRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMeals() {
     _repo.getBeefMeals(this);
    }

    @Override
    public void getChicken() {
        _repo.getChickenMeals(this);
    }

    @Override
    public void getDessert() {
        _repo.getDesertMeals(this);
    }

    @Override
    public void getLamb() {
        _repo.getLambMeals(this);
    }

    @Override
    public void getMiscellaneous() {
        _repo.getMiscellaneousMeals(this);
    }

    @Override
    public void getPasta() {
        _repo.getPastaMeals(this);
    }

    @Override
    public void getPork() {
        _repo.getPorkMeals(this);
    }

    @Override
    public void getSeaFood() {
        _repo.getSeaFoodMeals(this);
    }

    @Override
    public void getSide() {
        _repo.getSideMeals(this);
    }

    @Override
    public void getStarter() {
        _repo.getStarterMeals(this);
    }

    @Override
    public void getVegan() {
        _repo.getVeganMeals(this);
    }

    @Override
    public void getVegetarian() {
        _repo.getVegetarianMeals(this);
    }

    @Override
    public void getBreakFast() {
        _repo.getBreakFastMeals(this);
    }

    @Override
    public void getGoat() {
        _repo.getGoatMeals(this);
    }

    @Override
    public void addToFav(BeefMealsData beef) {
       _repo.insertMealsToFav(beef);
    }

    @Override
    public void getmealsByCountry(String countryname) {
        _repo.getMealsByCountryName(this,countryname);
    }


    @Override
    public void onSuccessResultMeal(List<BeefMealsData> List) {
        _view.showMeals(List);
        Log.i(TAG, " <<<<<<<<<<<<<onSuccessResult>>>>>>>>>>>>>>: "+List);
    }


    @Override
    public void onFailureResult(String errormsg) {
        Log.i(TAG, "onFailureResult in presenter: "+errormsg);
    }

}
