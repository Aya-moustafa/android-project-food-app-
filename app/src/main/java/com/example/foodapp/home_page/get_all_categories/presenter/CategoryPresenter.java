package com.example.foodapp.home_page.get_all_categories.presenter;

import android.util.Log;

import com.example.foodapp.home_page.get_all_categories.view.AllCategoriesView;
import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.AllCategory;

import com.example.foodapp.model.repository.CategoriesRepository;
import com.example.foodapp.model.countries.AllCountries;
import com.example.foodapp.network.HomeCallBack;
import com.example.foodapp.network.MealDetailsCallBack;

import java.util.List;

public class CategoryPresenter implements HomeCallBack, InCategoryPresenter , MealDetailsCallBack {

    private AllCategoriesView _view;
    CategoriesRepository _repo;

    public CategoryPresenter(AllCategoriesView _view, CategoriesRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getCategories(){
        _repo.getAllCategories(this);
    }

    @Override
    public void getCountries() {
        _repo.getAllCountries(this);
    }

    @Override
    public void getDailyMeal() {
        _repo.getDailyMeal(this);
    }


    @Override
    public void onSuccessResult(List<AllCategory> List) {
        _view.showData(List);
        Log.i("TAG:In Presenter", "<<<<<<<<<<<<<<<<<<<onSuccessResult: >>>>>>>>>>>>>>>>>>>>>>>>"+ List);
    }

    @Override
    public void onSuccessResultCon(List<AllCountries> List) {
        _view.showCountriesData(List);
        Log.i("TAG:In Presenter", "<<<<<<<<<<<<<<<<<<<onSuccessResultCon: >>>>>>>>>>>>>>>>>>>>>>>>"+List);
    }

    @Override
    public void onSuccessResultIngr(List<IngrediantsData> List) {
       _view.showIngrediantsData(List);
    }

    @Override
    public void onFailureResult(String errormsg) {
        _view.showErrorMsg(errormsg);
    }

    @Override
    public void onSuccessResultMealDetails(List<MealDetails> List) {
         _view.showDailyMeal(List);
    }
}
