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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.meals.presenter.MealsPresenter;
import com.example.foodapp.meals.presenter.MealsPresenterImp;
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

public class CountryFragment extends Fragment implements OnFavMealClickListener , BeefMealsView {
    private static final String TAG = "BeefFragment";
    RecyclerView recyclerView;
    MealsAdapter adapter;
    MealsPresenter presenter;
    String countryname;
    NavController navController;
    ImageView back;

    FirebaseFirestore db;
    ImageView imageMeal;

    Chip amrChip ,britiChip ,candChip , chinesChip ,croatChip , dutshChip , egyChip , flipChip ,frenchChip , greekChip , indiaChip , irishChip ,italianChip , kanyanChip;

    public CountryFragment() {
        // Required empty public constructor
    }
    public static CountryFragment newInstance(String param1, String param2) {
        CountryFragment fragment = new CountryFragment();
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
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();

        countryname = CountryFragmentArgs.fromBundle(getArguments()).getCountryname();
        back  = view.findViewById(R.id.backBtnMeal);
        amrChip = view.findViewById(R.id.beefchip);
        britiChip = view.findViewById(R.id.chickenchip);
        candChip = view.findViewById(R.id.desertchip);
        chinesChip  = view.findViewById(R.id.lambchip);
        croatChip = view.findViewById(R.id.miscellanchip);
        dutshChip = view.findViewById(R.id.pastanchip);
        egyChip = view.findViewById(R.id.porknchip);
        flipChip  = view.findViewById(R.id.seachip);
        frenchChip = view.findViewById(R.id.sidechip);
        greekChip = view.findViewById(R.id.starterchip);
        indiaChip = view.findViewById(R.id.veganchip);
        irishChip  = view.findViewById(R.id.vegetarianchip);
        italianChip = view.findViewById(R.id.breakfastchip);
        kanyanChip  = view.findViewById(R.id.goatchip);
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

        presenter.getmealsByCountry(countryname);

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
    public void onItemClickListener(View view, BeefMealsData mealsData) {
        CountryFragmentDirections.ActionCountryFragmentToMealFragment action = CountryFragmentDirections
                .actionCountryFragmentToMealFragment(mealsData);
        Navigation.findNavController(view).navigate(action);
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
}