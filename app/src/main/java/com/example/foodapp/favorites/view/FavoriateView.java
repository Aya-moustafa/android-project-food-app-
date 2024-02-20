package com.example.foodapp.favorites.view;

import androidx.lifecycle.LiveData;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoriateView {
     public void showFavoeiateMeals (Flowable<List<BeefMealsData>> beefFavList);
}
