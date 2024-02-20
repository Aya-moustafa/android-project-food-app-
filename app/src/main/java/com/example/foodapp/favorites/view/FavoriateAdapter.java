package com.example.foodapp.favorites.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriateAdapter extends RecyclerView.Adapter<FavoriateAdapter.FavViewHolder> {
        ImageView photo , removeIcon;
        Context context;

        List<BeefMealsData> beefMealsList;
        private OnFavMealClickListener beefListener ;


    public FavoriateAdapter(Context context, List<BeefMealsData> beefMealsList, OnFavMealClickListener beefListener) {
        this.context = context;
        this.beefMealsList = beefMealsList;
        this.beefListener = beefListener;
    }



    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.daily_meal_item,parent,false);
        FavViewHolder favoriateView =new FavViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : Favoriate Products >>>>>>>>>");
        return favoriateView;
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        if(beefMealsList != null) {
            BeefMealsData beefMeal = beefMealsList.get(position);
            holder.textTitle.setText(beefMeal.getStrMeal());
            Glide.with(context)
                    .load(beefMealsList.get(position).getStrMealThumb())
                    .into(photo);

            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    beefListener.onClickListener(beefMeal);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    beefListener.onItemClickListener(view ,beefMeal);
                }
            });


        }
    }

    public void setBeefMealsList(List<BeefMealsData> beefMealsList) {
        this.beefMealsList = beefMealsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return beefMealsList.size();
    }

    class FavViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        CardView cardview;
        Button remove;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.dailytxt);
            photo = itemView.findViewById(R.id.dailymealimg);
            remove = itemView.findViewById(R.id.insert);
            removeIcon = itemView.findViewById(R.id.insertdelete);
            removeIcon.setImageResource(R.drawable.remove_icon);
            // add.setPointerIcon();  to change the icon
            cardview = itemView.findViewById(R.id.cardviewDaily);
        }
    }
}
