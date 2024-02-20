package com.example.foodapp.one_meal.view;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.repository.CategoriesRepositoryImp;
import com.example.foodapp.one_meal.presenter.SpecificMealPresenterImp;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class MyBottomSheetFragment extends BottomSheetDialogFragment implements SpecificMealView {

    MealDetails meal ;
    BottomSheetDialog dialog;
    BottomSheetBehavior<View> bottomSheetBehavior;
    View rootView;

    SpecificMealPresenterImp presenterImp;
    ImageView photomeal;
    RecyclerView ingrediant;
    RecyclerView measures;
    TextView ingr1 , ingr2;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.bottom_sheet,container,false);
        rootView.setBackgroundResource(R.drawable.bottomsheet);
        rootView.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        rootView.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        rootView.setBackgroundColor(Color.TRANSPARENT);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        //set to behaviour to expand and minimum height to parent layout
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //Set Minimum height to parent view
        ConstraintLayout layout = dialog.findViewById(R.id.BottomSheetLayout);
        assert layout != null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        View bottomSheet = (View) view.getParent();
        bottomSheet.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        bottomSheet.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        bottomSheet.setBackgroundColor(Color.TRANSPARENT);
        photomeal = view.findViewById(R.id.mealphoto);
        ingr1 = view.findViewById(R.id.ing1);
        ingr2 = view.findViewById(R.id.ingr2);
       // String mealstr = MyBottomSheetFragmentArgs.fromBundle(getArguments()).getMealName();
        presenterImp = new SpecificMealPresenterImp(this, CategoriesRepositoryImp.getInstance(MealsLocalDataSourceImp.getInstance(getContext()),
                MealsPlanLocalDataSourceImp.getInstance(getContext())));
      //  meal = presenterImp.getMealByname(mealstr);

        updateUI(meal);


    }

    private void updateUI(MealDetails meal) {
        if (meal != null) {
            Glide.with(getContext())
                    .load(meal.getStrMealThumb())
                    .into(photomeal);
            ingr1.setText(meal.getStrIngredient1());
            ingr2.setText(meal.getStrIngredient2());
        }
    }

    @Override
    public void showMeal(List<MealDetails> mealDetails) {

    }
}