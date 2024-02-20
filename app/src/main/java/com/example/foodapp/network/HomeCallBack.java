package com.example.foodapp.network;

import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.all_category.AllCategory;
import com.example.foodapp.model.countries.AllCountries;

import java.util.List;

public interface HomeCallBack {
    public void onSuccessResult(List<AllCategory> List) ;
    public void onSuccessResultCon(List<AllCountries> List) ;
    public void onSuccessResultIngr(List<IngrediantsData> List) ;
    public void onFailureResult(String errormsg);
}
