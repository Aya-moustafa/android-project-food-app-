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

import com.example.foodapp.R;
import com.example.foodapp.home_page.view.homeFragmentDirections;
import com.example.foodapp.model.countries.AllCountries;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.countryViewHolder> {
    Context context;

    ImageView photo;

    private List<AllCountries> countriesList;

    private NavController navController ;


    public CountriesAdapter(Context context, List<AllCountries> countriesList) {
        this.context = context;
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public countryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_item_view,parent,false);
        countryViewHolder countryViewHolder = new countryViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : allCountries >>>>>>>>>");
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull countryViewHolder holder, int position) {
       AllCountries country = countriesList.get(position);
       holder.textTitle.setText(country.getStrArea());
       setCountryImage(country.getStrArea());
       String countryname = country.getStrArea();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController = Navigation.findNavController(view);
                homeFragmentDirections.ActionHomeFragmentToCountryFragment action =
                        homeFragmentDirections.actionHomeFragmentToCountryFragment(countryname);
                navController.navigate(action);

            }
        });
    }

    private void setCountryImage(String strArea) {
        switch (strArea) {
            case "American" :
                photo.setImageResource(R.drawable.amireca);
                break;
            case "British" :
                photo.setImageResource(R.drawable.british);
                break;
            case "Canadian" :
                photo.setImageResource(R.drawable.canadianpreview);
                break;
            case "Chinese" :
                photo.setImageResource(R.drawable.chines);
                break;
            case "Croatian" :
                photo.setImageResource(R.drawable.croatina);
                break;
            case "Dutch" :
                photo.setImageResource(R.drawable.dutch);
                break;
            case "Egyptian" :
                photo.setImageResource(R.drawable.koshry);
                break;
            case "Filipino" :
                photo.setImageResource(R.drawable.filipian);
                break;
            case "French" :
                photo.setImageResource(R.drawable.french);
                break;
            case "Greek" :
                photo.setImageResource(R.drawable.greek);
                break;
            case "Indian" :
                photo.setImageResource(R.drawable.canadianpreview);
                break;

        }
    }

    public void setCountriesList(List<AllCountries> countriesList) {
        this.countriesList = countriesList;
    }

    @Override
    public int getItemCount() {
        if (countriesList != null) {
            return countriesList.size();
        } else {
            return 0; // Return 0 if the list is null
        }
    }

    class countryViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        ConstraintLayout constraintLayout;
        public countryViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.countryImg);
            textTitle = itemView.findViewById(R.id.countryName);
            constraintLayout = itemView.findViewById(R.id.countryconstrain);
        }


    }
}
