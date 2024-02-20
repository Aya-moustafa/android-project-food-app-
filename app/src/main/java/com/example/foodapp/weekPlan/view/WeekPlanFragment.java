package com.example.foodapp.weekPlan.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.weekPlan.presenter.WeekPlanPresenter;
import com.example.foodapp.weekPlan.presenter.WeekPlanPresenterImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekPlanFragment extends Fragment implements onWeekPlanMealListener , WeekPlanView {

    RecyclerView recyclerView;

    WeekPlanAdapter adapter;
    WeekPlanPresenterImp presenter;

    public WeekPlanFragment() {
        // Required empty public constructor
    }
    public static WeekPlanFragment newInstance(String param1, String param2) {
        WeekPlanFragment fragment = new WeekPlanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.weekplan_recycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new WeekPlanAdapter(getContext(),new ArrayList<>() , this);
        presenter = new WeekPlanPresenterImp(this, CategoriesRepositoryImp.getInstance(MealsLocalDataSourceImp.getInstance(getContext()),
                MealsPlanLocalDataSourceImp.getInstance(getContext())));
        recyclerView.setAdapter(adapter);
        presenter.getStoredWeekMeals();
    }

    @Override
    public void onWeekPlanClickListener(MealWithDate mealWithDate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to remove " + mealWithDate.getStrMeal() + " from your Week Plan?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User confirmed deletion
                presenter.deleteMealsFromWeekPlan(mealWithDate);
                Toast.makeText(getActivity(), mealWithDate.getStrMeal() + " removed from Week Plan", Toast.LENGTH_SHORT).show();
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
    public void showAllWeekPlanMeals(Flowable<List<MealWithDate>> weekPlanMeals) {
        weekPlanMeals
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planMeals -> {
                            if (adapter != null) {
                                adapter.setWeekMealsList(planMeals);
                                adapter.notifyDataSetChanged();
                            }
                        } , error -> {
                            Log.i("TAG", "showAllWeekPlanMeals Error: "+error.getMessage());
                        } , () -> {
                            Log.i("TAG", "showAllWeekPlanMeals Done: ");
                        }
                );
    }
}