package com.example.foodapp.home_page.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.BottomNavigationViewKt;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityHomePageBinding;

import com.example.foodapp.weekPlan.view.WeekPlanFragment;
import com.example.foodapp.weekPlan.view.WeekPlanView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity implements homeFragment.LogoutListener {
    NavController navController;
    ActivityHomePageBinding binding;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        firebaseAuth = FirebaseAuth.getInstance();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, new homeFragment())
                .commit();
    }

    public void onLogout() {
        // Sign out the user
        FirebaseAuth.getInstance().signOut();

        // Redirect to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear back stack
        startActivity(intent);
        finish(); // Finish the current activity
    }

}


