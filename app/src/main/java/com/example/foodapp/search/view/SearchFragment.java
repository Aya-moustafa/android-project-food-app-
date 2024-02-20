package com.example.foodapp.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;

import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.home_page.get_all_categories.view.AllCategoriesView;
import com.example.foodapp.meals.view.BeefMealsView;
import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.AllCategory;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.countries.AllCountries;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.network.GenericRemoteDataSource;
import com.example.foodapp.search.presenter.SearchPresenter;
import com.example.foodapp.search.presenter.SearchPresenterImp;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchFragment extends Fragment implements SearchMealsView , BeefMealsView , AllCategoriesView {

    private final static String TAG = "SearchFragment";
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;

    CountryAdapter countryAdapter;
    CategoryAdapter categoryAdapter;
    IngrediantAdapter ingrediantAdapter;
    SearchPresenter searchPresenter;
    EditText searchEditText;
    Button  filterBtn;
    Chip categoryCh , countryCh , ingrediantCh , mealCh;

    String selectedCategory;

    private RecyclerView recyclerViewAfterFilter;
    private CompositeDisposable compositeDisposable ;



    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        TextInputLayout searchTextInputLayout = view.findViewById(R.id.searchTextInputLayout);
        searchEditText = view.findViewById(R.id.searchEditText);
        recyclerView = view.findViewById(R.id.search_recycle);
        filterBtn = view.findViewById(R.id.filterSeach);
        categoryCh= view.findViewById(R.id.Categorieschip);
        countryCh = view.findViewById(R.id.Countrieschip);
        ingrediantCh = view.findViewById(R.id.ingrediantschip);
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<>());
        categoryAdapter = new CategoryAdapter(getContext() ,new ArrayList<>());
        countryAdapter = new CountryAdapter(getContext() ,new ArrayList<>());
        ingrediantAdapter = new IngrediantAdapter(getContext() , new ArrayList<>());
        recyclerView.setHasFixedSize(true);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(searchAdapter);  // intially

        searchPresenter = new SearchPresenterImp(this,this,this,CategoriesRepositoryImp.getInstance(GenericRemoteDataSource.getInstance() ,
                MealsLocalDataSourceImp.getInstance(getContext()) , MealsPlanLocalDataSourceImp.getInstance(getContext())));

        categoryCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(categoryAdapter);
                searchPresenter.getAllcategories();
                searchEditText.setHint("Search by Category");
            }
        });

        countryCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(countryAdapter);
                searchPresenter.getAllcountries();
                searchEditText.setHint("Search by Country");
            }
        });

        ingrediantCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(ingrediantAdapter);
                searchPresenter.getAllIngrediants();
                searchEditText.setHint("Search by Ingrediant");
            }
        });

        Observable<String> observableSearchTxt = createSearchObservable();
        Disposable disposable = observableSearchTxt
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        query -> {
                            if (recyclerView.getAdapter() instanceof CategoryAdapter) {
                                filterCategories(query);
                            } else if (recyclerView.getAdapter() instanceof CountryAdapter) {
                                filterCountries(query);
                            } else if (recyclerView.getAdapter() instanceof IngrediantAdapter) {
                                filterIngrediant(query);
                            }
                        }
                );
        compositeDisposable.add(disposable);


    }

    private void filterIngrediant(String query) {
        if (ingrediantAdapter != null) {
            List<IngrediantsData> filteredIngrediant = new ArrayList<>();
            for (IngrediantsData ingrediant : ingrediantAdapter.getIngrediantsDataList()) {
                if (ingrediant.getStrIngredient().toLowerCase().contains(query.toLowerCase())) {
                    filteredIngrediant.add(ingrediant);
                }
            }
            ingrediantAdapter.setIngrediantsDataList(filteredIngrediant);
            ingrediantAdapter.notifyDataSetChanged();
        }
    }

    private void filterCountries(String query) {
        if (countryAdapter != null) {
            List<AllCountries> filteredCountries = new ArrayList<>();
            for (AllCountries country : countryAdapter.getCountriesList()) {
                if (country.getStrArea().toLowerCase().contains(query.toLowerCase())) {
                    filteredCountries.add(country);
                }
            }
            countryAdapter.setCountriesList(filteredCountries);
            countryAdapter.notifyDataSetChanged();
        }
    }

    private void filterCategories(String query) {
        if (categoryAdapter != null) {
            List<AllCategory> filteredCategories = new ArrayList<>();
            for (AllCategory category : categoryAdapter.getCategoriesList()) {
                if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredCategories.add(category);
                }
            }
            categoryAdapter.setCategoriesList(filteredCategories);
            categoryAdapter.notifyDataSetChanged();
        }
    }


    private Observable<String> createSearchObservable() {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    emitter.onNext(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

    @Override
    public void showResultSearch(List<MealDetails> list) {
    }

    @Override
    public void showErrorMsg() {
        Toast.makeText(getContext(), "No meals found with this name or letter", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMeals(List<BeefMealsData> beefMealsList) {

    }

    @Override
    public void showErrormsg(String error) {

    }

    @Override
    public void showData(List<AllCategory> categoriesList) {
        if(categoryAdapter != null) {
            categoryAdapter.setCategoriesList(categoriesList);
            categoryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showCountriesData(List<AllCountries> countriesList) {
               if(countryAdapter != null) {
                   countryAdapter.setCountriesList(countriesList);
                   countryAdapter.notifyDataSetChanged();
               }
    }

    @Override
    public void showIngrediantsData(List<IngrediantsData> IngrediantsList) {
        if (ingrediantAdapter != null){
            ingrediantAdapter.setIngrediantsDataList(IngrediantsList);
            ingrediantAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showDailyMeal(List<MealDetails> dailyMeal) {
    }

    @Override
    public void showErrorMsg(String error) {

    }
}