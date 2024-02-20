package com.example.foodapp.favorites.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.favorites.presenter.FavoriateMealsPresenterImp;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesFragment extends Fragment implements OnFavMealClickListener, FavoriateView {

    private final static String TAG = "FavoritesFragment";
    RecyclerView recyclerView;
    FavoriateAdapter adapter;
    FavoriateMealsPresenterImp presenter;
    FirebaseFirestore db;
    SharedPreferences sharedPreferences;

    String userEmail;

    public FavoritesFragment() {
        // Required empty public constructor
    }
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(getContext());
        db = FirebaseFirestore.getInstance();
        sharedPreferences = getContext().getSharedPreferences(LoginActivity.PREFERENCE , Context.MODE_PRIVATE);
        userEmail = sharedPreferences.getString("user_email","");
        recyclerView = view.findViewById(R.id.recycle_favoriate);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new FavoriateAdapter(getContext(), new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
        presenter = new FavoriateMealsPresenterImp(this, CategoriesRepositoryImp.getInstance(MealsLocalDataSourceImp.getInstance(getContext()) , MealsPlanLocalDataSourceImp.getInstance(getContext())));

        presenter.getStoredMeals();
        fetchFavoriteMeals();
    }

    private void fetchFavoriteMeals() {
        if(!userEmail.isEmpty()){
             db.collection("favoriatemeals").whereEqualTo("userEmail",userEmail)
                     .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                         @Override
                         public void onComplete(@NonNull Task<QuerySnapshot> task) {
                             if (task.isSuccessful()) {
                                 List<BeefMealsData> favMealsList = new ArrayList<>();
                                 for (QueryDocumentSnapshot doc : task.getResult()) {
                                     BeefMealsData favMeal = doc.toObject(BeefMealsData.class);
                                     favMeal.setIdMeal(doc.getId());
                                     favMealsList.add(favMeal);
                                 }
                                 adapter.setBeefMealsList(favMealsList);
                                 adapter.notifyDataSetChanged();
                                 Log.i(TAG, "onComplete: the fav meals from firebase" + favMealsList);

                             }
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Toast.makeText(getContext(), "Failed to get the Favorite Meals", Toast.LENGTH_LONG).show();
                         }
                     });
        }
    }

    @Override
    public void onClickListener(BeefMealsData beefMeal) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to remove " + beefMeal.getStrMeal() + " from Favorites?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User confirmed deletion
                presenter.deleteMealsFromFavoriate(beefMeal);
                adapter.notifyDataSetChanged();
                db.collection("favoriatemeals")
                        .document(beefMeal.getIdMeal())
                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), beefMeal.getStrMeal() + " removed from Favorites", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Failed to remove " + beefMeal.getStrMeal() + " from Favorites", Toast.LENGTH_SHORT).show();
                            }
                        });
                Toast.makeText(getActivity(), beefMeal.getStrMeal() + " removed from Favorite", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User canceled deletion
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onItemClickListener(View view, BeefMealsData beefMealsData) {
       /*  FavoritesFragmentDirections.ActionFavoritesFragmentToMealFragment action  = FavoritesFragmentDirections
                 .actionFavoritesFragmentToMealFragment(mealName);
        Navigation.findNavController(view).navigate(action);  */
    }

    @Override
    public void showFavoeiateMeals(Flowable<List<BeefMealsData>> favMealsObservable) {
        favMealsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favMeals -> {
                            if (adapter != null) {
                                adapter.setBeefMealsList(favMeals);
                                adapter.notifyDataSetChanged();
                            }
                        } , error -> {
                            Log.i(TAG, "showFavoeiateMeals Error: "+error.getMessage());
                        } , () -> {
                            Log.i(TAG, "showFavoeiateMeals Done: ");
                        }
                );
    }
}