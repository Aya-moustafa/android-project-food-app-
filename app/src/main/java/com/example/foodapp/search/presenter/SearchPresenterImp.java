package com.example.foodapp.search.presenter;

import com.example.foodapp.home_page.get_all_categories.view.AllCategoriesView;
import com.example.foodapp.meals.view.BeefMealsView;
import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.AllCategory;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.countries.AllCountries;
import com.example.foodapp.model.repository.CategoriesRepository;
import com.example.foodapp.network.HomeCallBack;
import com.example.foodapp.network.MealDetailsCallBack;
import com.example.foodapp.network.MealsCallBack;
import com.example.foodapp.search.view.SearchMealsView;

import java.util.List;

public class SearchPresenterImp implements MealDetailsCallBack, SearchPresenter , HomeCallBack , MealsCallBack {
    SearchMealsView _view;
    AllCategoriesView _viewcategory;
    BeefMealsView _viewMealsofCategory;
    CategoriesRepository _repo;

    public SearchPresenterImp(SearchMealsView _view,AllCategoriesView _viewcategory,BeefMealsView _viewMealsofCategory ,CategoriesRepository _repo) {
        this._view = _view;
        this._repo = _repo;
        this._viewcategory = _viewcategory;
        this._viewMealsofCategory = _viewMealsofCategory;

    }

    public SearchPresenterImp(SearchMealsView _view,BeefMealsView _viewMealsofCategory ,CategoriesRepository _repo) {
        this._view = _view;
        this._repo = _repo;
        this._viewMealsofCategory = _viewMealsofCategory;
    }


    @Override
    public void getSearchMealsByFirstLetter(String firstLetter) {
        _repo.getMealsByFirstLetter(this , firstLetter);
    }

    @Override
    public void getSearchMealsByFirstName(String mealName) {
        _repo.getMealByName(this , mealName);
    }

    @Override
    public void getMealsByCategoryName(String categoryName) {
        _repo.getMealsByCategoryName(this,categoryName);
    }

    @Override
    public void getMealsByCountryName(String countryName) {
        _repo.getMealsByCountryName(this,countryName);
    }

    @Override
    public void getMealsByIngrediantName(String ingrediantName) {
        _repo.getMealsByCountryName(this,ingrediantName);

    }

    @Override
    public void getMealsBycategoryAndname(String name, String category) {
        if (name.isEmpty()) {
            _repo.getMealsByCategoryName(this,category);
        } else {
            // Continue with searching by meal name
            _repo.getMealsByCategoryandName(this,category,name);
        }
    }

    @Override
    public void getMealsBycountryAndname(String name, String country) {
        _repo.getMealsByCountryandName(this,country,name);
    }

    @Override
    public void getMealsByingrediantAndname(String name, String ingrediant) {
       _repo.getMealsByIngrediantandName(this,ingrediant,name);
    }

    @Override
    public void addToFav(BeefMealsData beef) {
        _repo.insertMealsToFav(beef);
    }

    @Override
    public void getAllcountries() {
        _repo.getAllCountries(this);
    }

    @Override
    public void getAllcategories() {
       _repo.getAllCategories(this);
    }

    @Override
    public void getAllIngrediants() {
       _repo.getAllIngrediants(this);
    }


    @Override
    public void onSuccessResultMealDetails(List<MealDetails> List) {
        _view.showResultSearch(List);
    }

    @Override
    public void onSuccessResult(List<AllCategory> List) {
          _viewcategory.showData(List);
    }

    @Override
    public void onSuccessResultCon(List<AllCountries> List) {
             _viewcategory.showCountriesData(List);
    }

    @Override
    public void onSuccessResultIngr(List<IngrediantsData> List) {
        _viewcategory.showIngrediantsData(List);
    }

    @Override
    public void onSuccessResultMeal(List<BeefMealsData> List) {
        _viewMealsofCategory.showMeals(List);
    }

    @Override
    public void onFailureResult(String errormsg) {

    }
}
