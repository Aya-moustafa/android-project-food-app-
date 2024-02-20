package com.example.foodapp.home_page.get_all_categories.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.home_page.view.homeFragmentDirections;
import com.example.foodapp.model.all_category.AllCategory;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private final static String TAG = "productAdapter";
    ImageView photo;
    Context context;
    private List<AllCategory> categoriesList;

    private NavController navController ;

    public CategoriesAdapter(Context context, List<AllCategory> categoriesList , NavController navController) {
        this.context = context;
        this.categoriesList = categoriesList;
        this.navController  = navController;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        Log.i(TAG,"<<<<<<< OnCreateViewHolder : Categories >>>>>>>>>");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         AllCategory category = categoriesList.get(position);
         String categoryname = category.getStrCategory();
         holder.textTitle.setText(category.getStrCategory());
         Glide.with(context)
                .load(categoriesList.get(position).getStrCategoryThumb())
                .into(photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController = Navigation.findNavController(view);
                homeFragmentDirections.ActionHomeFragmentToBeefFragment action =
                        homeFragmentDirections.actionHomeFragmentToBeefFragment(categoryname);
                navController.navigate(action);

            }
        });

    }
    public void setCategoriesList(List<AllCategory> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public int getItemCount() {
        if (categoriesList != null) {
            return categoriesList.size();
        } else {
            return 0; // Return 0 if the list is null
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        ConstraintLayout constraintLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.catname);
            photo = itemView.findViewById(R.id.countryImg);
            constraintLayout = itemView.findViewById(R.id.countryconstrain);
        }
    }
}
