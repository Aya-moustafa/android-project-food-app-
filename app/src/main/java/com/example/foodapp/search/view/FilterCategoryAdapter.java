package com.example.foodapp.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.all_category.AllCategory;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.FilterCateViewHolder> {

    List<AllCategory> listCategory;
    Context context;
    String selectedCategoryName;

    private final static String TAG = "filterByCategoryAdapter" ;

    public FilterCategoryAdapter(List<AllCategory> listCategory, Context context) {
        this.listCategory = listCategory;
        this.context = context;
    }

    @NonNull
    @Override
    public FilterCateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.filter_by_category,parent,false);
        FilterCateViewHolder filterCateViewHolder = new FilterCateViewHolder(view);
        Log.i(TAG,"<<<<<<< OnCreateViewHolder : filterByCategory >>>>>>>>>");
        return filterCateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCateViewHolder holder, int position) {
         AllCategory category = listCategory.get(position);
         holder.categoryChip.setText(category.getStrCategory());
        if (category.getStrCategory().equals(selectedCategoryName)) {
            holder.categoryChip.setChipBackgroundColorResource(R.color.green);
        } else {
            holder.categoryChip.setChipBackgroundColorResource(R.color.gray);
        }
        holder.categoryChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCategoryName = category.getStrCategory();
                notifyDataSetChanged();
            }
        });
    }

    public String getSelectedCategoryName() {
        return selectedCategoryName;
    }

    public void setListCategory(List<AllCategory> listCategory) {
        this.listCategory = listCategory;
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }


    class FilterCateViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayout;
        Chip categoryChip;

        int colorchipChecked =  R.color.green;
        int colorchipUnChecked =  R.color.gray;
        public View layout;
        public FilterCateViewHolder(@NonNull View itemView) {
            super(itemView);

           // categoryChip = itemView.findViewById(R.id.categoryChip);
            frameLayout = itemView.findViewById(R.id.categoryframe);
        }
    }
}
