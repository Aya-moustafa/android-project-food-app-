package com.example.foodapp.meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.meals.presenter.MealsPresenter;
import com.example.foodapp.meals.presenter.MealsPresenterImp;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.network.GenericRemoteDataSource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeefFragment extends Fragment implements BeefMealsView, OnFavMealClickListener {

    private static final String TAG = "BeefFragment";
    RecyclerView recyclerView;
    MealsAdapter adapter;
    MealsPresenter presenter;
    String categoryName;
    NavController navController;
    ImageView back;

    FirebaseFirestore db;
    ImageView imageMeal;

    Chip beefChip ,chickenChip ,dessertChip , lambChip ,miscelleneousChip , pastaChip , porkChip , seafoodChip , sideChip , starterChip , veganChip , vegetarianChip ,breakfastChip ,goatChip;

    public BeefFragment() {
        // Required empty public constructor
    }

    public static BeefFragment newInstance(String param1, String param2) {
        BeefFragment fragment = new BeefFragment();
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
        return inflater.inflate(R.layout.fragment_beef, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();

        categoryName = BeefFragmentArgs.fromBundle(getArguments()).getCategoryname();
        back  = view.findViewById(R.id.backBtnMeal);
        beefChip = view.findViewById(R.id.beefchip);
        chickenChip = view.findViewById(R.id.chickenchip);
        dessertChip = view.findViewById(R.id.desertchip);
        lambChip  = view.findViewById(R.id.lambchip);
        miscelleneousChip = view.findViewById(R.id.miscellanchip);
        pastaChip = view.findViewById(R.id.pastanchip);
        porkChip = view.findViewById(R.id.porknchip);
        seafoodChip  = view.findViewById(R.id.seachip);
        sideChip = view.findViewById(R.id.sidechip);
        starterChip = view.findViewById(R.id.starterchip);
        veganChip = view.findViewById(R.id.veganchip);
        vegetarianChip  = view.findViewById(R.id.vegetarianchip);
        breakfastChip = view.findViewById(R.id.breakfastchip);
        goatChip  = view.findViewById(R.id.goatchip);
        imageMeal = view.findViewById(R.id.imageMeal);
        recyclerView = view.findViewById(R.id.recycle_beef);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the adapter with an empty list
        adapter = new MealsAdapter(getContext(), new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000); // Customize animation duration if needed
        recyclerView.setItemAnimator(animator);

        presenter = new MealsPresenterImp(this, CategoriesRepositoryImp.getInstance(GenericRemoteDataSource.getInstance() ,
                MealsLocalDataSourceImp.getInstance(getContext()) , MealsPlanLocalDataSourceImp.getInstance(getContext())));
        fetchDataForCategory(categoryName);
     /*   back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();
            }
        });  */

        beefChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Beef");
            }
        });
        chickenChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Chicken");
            }
        });
        dessertChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Dessert");
            }
        });
        lambChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Lamb");
            }
        });
        miscelleneousChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Miscellaneous");
            }
        });
        pastaChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Pasta");
            }
        });

        porkChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Pork");
            }
        });

        seafoodChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Seafood");
            }
        });

        sideChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Side");
            }
        });

        starterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Starter");
            }
        });

        veganChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Vegan");
            }
        });

        vegetarianChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Vegetarian");
            }
        });

        breakfastChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Breakfast");
            }
        });

        goatChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataForCategory("Goat");
            }
        });

    }

    private void fetchDataForCategory(String categoryName) {
        switch (categoryName){
            case "Beef" :
                imageMeal.setImageResource(R.drawable.meattt);
                presenter.getMeals();
                break;
            case "Chicken" :
                imageMeal.setImageResource(R.drawable.chickk);
                presenter.getChicken();
                break;
            case "Dessert" :
                imageMeal.setImageResource(R.drawable.finaldessercop);
                presenter.getDessert();
                break;
            case "Lamb" :
                imageMeal.setImageResource(R.drawable.lambco);
                presenter.getLamb();
                break;
            case "Miscellaneous" :
                presenter.getMiscellaneous();
                break;
            case "Pasta" :
                imageMeal.setImageResource(R.drawable.pastaa);
                presenter.getPasta();
                break;
            case "Pork" :
                presenter.getPork();
                break;
            case "Seafood" :
                presenter.getSeaFood();
                break;
            case "Side" :
                presenter.getSide();
                break;
            case "Starter" :
                presenter.getStarter();
                break;
            case "Vegan" :
                presenter.getVegan();
                break;
            case "Vegetarian" :
                presenter.getVegetarian();
                break;
            case "Breakfast" :
                presenter.getBreakFast();
                break;
            case "Goat" :
                presenter.getGoat();
                break;
        }
    }

    @Override
    public void showMeals(List<BeefMealsData> beefMealsList) {
        adapter.setBeefMealsList(beefMealsList);
        adapter.notifyDataSetChanged();
        Log.i(TAG,"<<<<<<<<showMeals>>>>>>>>>>>>>"+beefMealsList);
    }

    @Override
    public void showErrormsg(String error) {
        Log.i(TAG,"<<<<<<<<<<errorBeefmsg>>>>>>>>>>>qw" +error);
    }

    @Override
    public void onClickListener(BeefMealsData beefMeal) {
        presenter.addToFav(beefMeal);
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
    public void onItemClickListener(View view, BeefMealsData beefMealsData) {
        BeefFragmentDirections.ActionBeefFragmentToMealFragment action = BeefFragmentDirections
                .actionBeefFragmentToMealFragment(beefMealsData);
        Navigation.findNavController(view).navigate(action);
    }
}