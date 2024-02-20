package com.example.foodapp.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.home_page.get_all_categories.view.CategoriesAdapter;
import com.example.foodapp.home_page.view.homeFragmentDirections;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.model.all_category.AllCategory;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    CircleImageView photo;
    Context context;
    private List<AllCategory> categoriesList;
    private NavController navController ;
    private OnFavMealClickListener listener;

    public CategoryAdapter( Context context,List<AllCategory> categoriesList) {
        this.categoriesList = categoriesList;
        this.context =context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : Categories >>>>>>>>>");
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
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
                SearchFragmentDirections.ActionSearchFragmentToSearchCategoryFragment action =
                        SearchFragmentDirections.actionSearchFragmentToSearchCategoryFragment(categoryname);
                navController.navigate((NavDirections) action);
            }
        });
    }

    public List<AllCategory> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<AllCategory> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private Button insertFav;
        FrameLayout frameLayout;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catname);
            photo = itemView.findViewById(R.id.countryImg);
            insertFav = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);

        }
    }
}
