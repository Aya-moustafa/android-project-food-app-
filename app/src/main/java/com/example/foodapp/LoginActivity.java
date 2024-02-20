package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.home_page.view.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    String email ;
    public static final String PREFERENCE = "PREFERENCE";
    private FirebaseAuth firebaseAuth;
    EditText userEmail , userPassword;
    TextView signupRedirection;
    CheckBox remember_me;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        userEmail = findViewById(R.id.loginEmail);
        userPassword = findViewById(R.id.loginpassword);
        signupRedirection = findViewById(R.id.signuonow);
        login = findViewById(R.id.loginBtn);
        remember_me = findViewById(R.id.remeberme);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = userEmail.getText().toString().trim();
                String pass = userPassword.getText().toString().trim();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!pass.isEmpty()){
                        firebaseAuth.signInWithEmailAndPassword(email ,pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("user_email",email);
                                        editor.apply();
                                        Toast.makeText(LoginActivity.this,"SignIn Successfuly",Toast.LENGTH_LONG).show();
                                        //Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment);
                                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this,"SignIn Failed",Toast.LENGTH_LONG).show();

                                    }
                                });
                    }else {
                        userPassword.setError("Password Can't be Empty");
                    }
                } else if (email.isEmpty()) {
                    userEmail.setError("Email can't be empty.");
                }else {
                    userEmail.setError("Please enter valid email..");
                }
            }
        });


        signupRedirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_signUpFragment);
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
