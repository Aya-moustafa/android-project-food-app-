package com.example.foodapp.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.meals.view.BeefMealsView;
import com.example.foodapp.meals.view.MealsAdapter;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.network.GenericRemoteDataSource;
import com.example.foodapp.search.presenter.SearchPresenter;
import com.example.foodapp.search.presenter.SearchPresenterImp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CountrySearchFragment extends Fragment implements BeefMealsView, SearchMealsView , OnFavMealClickListener {
    private final static String TAG = "SearchCountryFragment";
    EditText searchCountriesEditText;
    RecyclerView recyclerViewCountries;
    MealsAdapter adapter;
    private CompositeDisposable compositeDisposable ;
    SearchAdapter searchAdapter;
    SearchPresenter searchPresenter;
    String countryname;
    FirebaseFirestore db;

    public CountrySearchFragment() {
        // Required empty public constructor
    }

    public static CountrySearchFragment newInstance(String param1, String param2) {
        CountrySearchFragment fragment = new CountrySearchFragment();
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
        return inflater.inflate(R.layout.fragment_country_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        searchCountriesEditText = view.findViewById(R.id.searchcountryEditText);
        recyclerViewCountries = view.findViewById(R.id.searchcountry_recycle);
        countryname = CountrySearchFragmentArgs.fromBundle(getArguments()).getCountryName();
        recyclerViewCountries.setHasFixedSize(true);
        compositeDisposable = new CompositeDisposable();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerViewCountries.setLayoutManager(gridLayoutManager);

        // Initialize the adapter with an empty list
        adapter = new MealsAdapter(getContext(), new ArrayList<>() , this);
        searchAdapter = new SearchAdapter(getContext(),new ArrayList<>());
        recyclerViewCountries.setAdapter(adapter);
        searchPresenter = new SearchPresenterImp(this,this, CategoriesRepositoryImp.getInstance(GenericRemoteDataSource.getInstance(),
                MealsLocalDataSourceImp.getInstance(getContext()) , MealsPlanLocalDataSourceImp.getInstance(getContext())));
        searchPresenter.getMealsByCountryName(countryname);

        Observable<String> observableSearchTxt = createSearchObservable();
        Disposable disposable = observableSearchTxt
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        query -> {
                            if (TextUtils.isEmpty(query)) {
                                recyclerViewCountries.setAdapter(adapter);
                            } else {
                                searchPresenter.getMealsBycountryAndname(query,countryname);
                            }

                        } , Throwable -> {
                            Log.i(TAG, "onViewCreated: onSearchByMealFailerCountryandMealName "+Throwable);
                        }
                );
        compositeDisposable.add(disposable);
    }

    private Observable<String> createSearchObservable() {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            searchCountriesEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    emitter.onNext(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 0) {
                        emitter.onNext("");
                    }
                }
            });

        });
    }

    @Override
    public void showMeals(List<BeefMealsData> beefMealsList) {
              if(adapter != null) {
                  adapter.setBeefMealsList(beefMealsList);
                  adapter.notifyDataSetChanged();
              }
    }

    @Override
    public void showErrormsg(String error) {

    }

    @Override
    public void showResultSearch(List<MealDetails> list) {
        recyclerViewCountries.setAdapter(searchAdapter);
        if(searchAdapter != null) {
            searchAdapter.setListOfsearchMeals(list);
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorMsg() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

    @Override
    public void onClickListener(BeefMealsData beefMeal) {
        searchPresenter.addToFav(beefMeal);
        Map<String, Object> favmeal = new HashMap<>();
        favmeal.put("strMeal", beefMeal.getStrMeal());
        favmeal.put("strMealThumb" , beefMeal.getStrMealThumb());
        favmeal.put("idMeal" , beefMeal.getIdMeal());
        favmeal.put("userEmail",beefMeal.getUserEmail());
        db.collection("favoriatemeals").add(favmeal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext() , "the favoriate meal added successfully.",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext() , "Fail to add the favoriate meal...",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClickListener(View view, BeefMealsData mealsData) {
        CountrySearchFragmentDirections.ActionCountrySearchFragmentToMealFragment action= CountrySearchFragmentDirections
                .actionCountrySearchFragmentToMealFragment(mealsData);
        Navigation.findNavController(view).navigate(action);
    }
}