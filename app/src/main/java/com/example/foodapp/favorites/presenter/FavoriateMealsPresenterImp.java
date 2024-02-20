package com.example.foodapp.favorites.presenter;

import com.example.foodapp.favorites.view.FavoriateView;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepository;

public class FavoriateMealsPresenterImp implements FavoriateMealsPresenter {

    FavoriateView _view;
    CategoriesRepository _repo;

    public FavoriateMealsPresenterImp(FavoriateView _view, CategoriesRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getStoredMeals() {
       _view.showFavoeiateMeals(_repo.getStoredMeals());
    }

    @Override
    public void deleteMealsFromFavoriate(BeefMealsData beefMealsData) {
        _repo.deleteMealsFromFav(beefMealsData);
    }

}
