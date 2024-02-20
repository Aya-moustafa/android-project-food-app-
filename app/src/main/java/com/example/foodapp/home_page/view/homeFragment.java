package com.example.foodapp.home_page.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.home_page.get_all_categories.presenter.CategoryPresenter;
import com.example.foodapp.home_page.get_all_categories.view.AllCategoriesView;
import com.example.foodapp.home_page.get_all_categories.view.CategoriesAdapter;
import com.example.foodapp.home_page.get_all_categories.view.CountriesAdapter;
import com.example.foodapp.home_page.get_all_categories.view.DailyAdapter;
import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.AllCategory;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.model.countries.AllCountries;
import com.example.foodapp.network.GenericRemoteDataSource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment implements AllCategoriesView {


    NavController navController;
    RecyclerView recyclerView;

    RecyclerView recycle_Countries;

    RecyclerView recycle_Daily;
    CategoriesAdapter adapter;

    CountriesAdapter adapter_Countries;

    DailyAdapter dailyAdapter;

    CategoryPresenter presenter;

    Button favoriates , weekplan;

    FloatingActionButton logout;

    Button  searcheditTXT;
    private FirebaseAuth firebaseAuth;


    private final static String TAG = "Home_Fragment";

    public homeFragment() {
        // Required empty public constructor
    }

    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.recyclerView);
        favoriates = view.findViewById(R.id.btnFav);
        searcheditTXT = view.findViewById(R.id.searchTtxt);
        recycle_Countries =view.findViewById(R.id.recycle_ingre);
        recycle_Daily =view.findViewById(R.id.recycle_daily);
        weekplan = view.findViewById(R.id.btnplan);
        logout = view.findViewById(R.id.logoutbtn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new CategoriesAdapter(getContext(),new ArrayList<>(),navController);
        dailyAdapter = new DailyAdapter(getContext(),new ArrayList<>());
        LinearLayoutManager countryLayoutManager = new LinearLayoutManager(getContext());
        adapter_Countries = new CountriesAdapter(getContext(),new ArrayList<>());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        countryLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recycle_Countries.setLayoutManager(countryLayoutManager);
        LinearLayoutManager dailyLayoutManager = new LinearLayoutManager(getContext());
        dailyLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycle_Daily.setLayoutManager(dailyLayoutManager);



        presenter = new CategoryPresenter(this, CategoriesRepositoryImp.getInstance(GenericRemoteDataSource.getInstance() ,
                MealsLocalDataSourceImp.getInstance(getContext()) , MealsPlanLocalDataSourceImp.getInstance(getContext())));
        presenter.getCategories();
        presenter.getCountries();
        presenter.getDailyMeal();
        favoriates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_homeFragment_to_favoritesFragment);
                Log.i(TAG,"<<<<<<<<<<<<<<<<<The navigate to favoriate fragment success>>>>>>>>>>>>>>>>>>>>");
            }
        });



        searcheditTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment);            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof LogoutListener) {
                    ((LogoutListener) getActivity()).onLogout();
                }
            }
        });

        weekplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_weekPlanFragment);
            }
        });

    }

    public interface LogoutListener {
        void onLogout();
    }


    @Override
    public void showData(List<AllCategory> categoList) {
        recyclerView.setAdapter(adapter);
        adapter.setCategoriesList(categoList);
        adapter.notifyDataSetChanged();
        Log.i(TAG, "showData in fragment:"+categoList);
    }

    @Override
    public void showCountriesData(List<AllCountries> countriesList) {
        recycle_Countries.setAdapter(adapter_Countries);
       adapter_Countries.setCountriesList(countriesList);
       adapter_Countries.notifyDataSetChanged();
        Log.i(TAG, "showCountriesData in the fragment: "+countriesList);
    }

    @Override
    public void showIngrediantsData(List<IngrediantsData> IngrediantsList) {

    }

    @Override
    public void showDailyMeal(List<MealDetails> dailyMeal) {
       recycle_Daily.setAdapter(dailyAdapter);
       dailyAdapter.setDailyMealsList(dailyMeal);
       dailyAdapter.notifyDataSetChanged();


    }

    @Override
    public void showErrorMsg(String error) {
        Log.e(TAG, error);
    }
}