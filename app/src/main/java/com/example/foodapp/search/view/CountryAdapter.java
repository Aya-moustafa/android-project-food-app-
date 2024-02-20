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
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.home_page.get_all_categories.view.CountriesAdapter;
import com.example.foodapp.model.countries.AllCountries;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{
    Context context;

    ImageView photo;

    private List<AllCountries> countriesList;

    NavController navController;

    public CountryAdapter(Context context, List<AllCountries> countriesList) {
        this.context = context;
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : allCountries >>>>>>>>>");
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        AllCountries country = countriesList.get(position);
        holder.textTitle.setText(country.getStrArea());
        String countryname = country.getStrArea();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController = Navigation.findNavController(view);
                SearchFragmentDirections.ActionSearchFragmentToCountrySearchFragment action =
                        SearchFragmentDirections.actionSearchFragmentToCountrySearchFragment(countryname);
                navController.navigate((NavDirections) action);
            }
        });
      //  setCountryImage(country.getStrArea());
    }

    public List<AllCountries> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(List<AllCountries> countriesList) {
        this.countriesList = countriesList;
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private Button insertFav;
        FrameLayout frameLayout;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catname);
            insertFav = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);

        }
    }
}
