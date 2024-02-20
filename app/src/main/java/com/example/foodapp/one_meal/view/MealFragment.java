package com.example.foodapp.one_meal.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.network.GenericRemoteDataSource;
import com.example.foodapp.one_meal.presenter.SpecificMealPresenter;
import com.example.foodapp.one_meal.presenter.SpecificMealPresenterImp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;

import java.util.Calendar;

public class MealFragment extends Fragment implements  OnFavMealClickListener {


    private final static String TAG = "MealFragment";

   BeefMealsData meal;

   MealWithDate mealWithDate;

   String dateOfMeal;
   Button  saveBtn;
   Button  mealplan;
    private OnFavMealClickListener listener;
   SpecificMealPresenter presenter;

   ImageView mealImage ,backBtn , swapUp;
   TextView mealText;
    public MealFragment() {
        // Required empty public constructor
    }

    public static MealFragment newInstance(String param1, String param2) {
        MealFragment fragment = new MealFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFavMealClickListener) {
            listener = (OnFavMealClickListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meal = new BeefMealsData();
        mealWithDate = new MealWithDate();
        mealImage= view.findViewById(R.id.onemealImage);
        mealText = view.findViewById(R.id.onemealName);
        saveBtn  = view.findViewById(R.id.savebtn);
        backBtn  = view.findViewById(R.id.backBtnMeal);
        swapUp   = view.findViewById(R.id.swapup);
        mealplan = view.findViewById(R.id.planbtn);
        meal = MealFragmentArgs.fromBundle(getArguments()).getMealData();
        //Intialize Calender
        Calendar calendar = Calendar.getInstance();

        int year  = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day   = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i(TAG, "onViewCreated: the return object from BeefFragment " + meal.getStrMeal());
        updateUIofMealFragment(meal);
        presenter = new SpecificMealPresenterImp(CategoriesRepositoryImp.getInstance(GenericRemoteDataSource.getInstance(),
               MealsLocalDataSourceImp.getInstance(getContext()), MealsPlanLocalDataSourceImp.getInstance(getContext())));
      //  presenter.getMealByname(mealName);

        swapUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetFragment bottomSheetFragment = new MyBottomSheetFragment();
                bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
              /*  MealFragmentDirections.ActionMealFragmentToMyBottomSheetFragment action = MealFragmentDirections
                        .actionMealFragmentToMyBottomSheetFragment(meal.getStrMeal());*/
            }
        });

        saveBtn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (meal != null) {
                        presenter.addToFavMeals(meal);
                        Toast.makeText(getActivity(), meal.getStrMeal() + " Saved To Favorite", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        mealplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfWeek) {
                        String sDate = year + "/" + month + "/" + dayOfWeek;
                        dateOfMeal = sDate;
                        mealWithDate.setIdMeal(meal.getIdMeal());
                        mealWithDate.setStrMeal(meal.getStrMeal());
                        mealWithDate.setStrMealThumb(meal.getStrMealThumb());
                        mealWithDate.setMealDate(dateOfMeal);
                        Log.i(TAG, "onDateSet: insert this meal with date to database"+mealWithDate.getStrMeal()+mealWithDate.getMealDate());
                        // Insert mealWithDate into the database
                        if (mealWithDate != null) {
                            presenter.addMealToPlan(mealWithDate);
                            Toast.makeText(getActivity(), mealWithDate.getStrMeal() + " Saved To your Week Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, year, month, day
                );
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
    }

    public void updateUIofMealFragment(BeefMealsData mealData) {
        mealText.setText(mealData.getStrMeal());
        Glide.with(getContext())
                .load(mealData.getStrMealThumb())
                .into(mealImage);
    }

    @Override
    public void onClickListener(BeefMealsData beefMeal) {

    }

    @Override
    public void onItemClickListener(View view, BeefMealsData beefMealsData) {

    }

}