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
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.countries.AllCountries;

import java.util.List;

public class IngrediantAdapter extends RecyclerView.Adapter<IngrediantAdapter.IngrediantViewHolder> {

    Context context;

    ImageView photo;

    private List<IngrediantsData> ingrediantsDataList;

    public IngrediantAdapter(Context context, List<IngrediantsData> ingrediantsDataList) {
        this.context = context;
        this.ingrediantsDataList = ingrediantsDataList;
    }

    @NonNull
    @Override
    public IngrediantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
        IngrediantViewHolder ingrediantViewHolder = new IngrediantViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : allCountries >>>>>>>>>");
        return ingrediantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngrediantViewHolder holder, int position) {
        IngrediantsData ingrediant = ingrediantsDataList.get(position);
        holder.textTitle.setText(ingrediant.getStrIngredient());
    }

    public void setIngrediantsDataList(List<IngrediantsData> ingrediantsDataList) {
        this.ingrediantsDataList = ingrediantsDataList;
    }

    public List<IngrediantsData> getIngrediantsDataList() {
        return ingrediantsDataList;
    }

    @Override
    public int getItemCount() {
        return ingrediantsDataList.size();
    }

    class IngrediantViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private Button insertFav;
        FrameLayout frameLayout;
        public IngrediantViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catname);
            insertFav = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);

        }
    }
}
