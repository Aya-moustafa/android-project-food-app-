package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText username , useremail ,userpassword , userconfirmPass;
    Button signup;
    TextView loginRedirection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        useremail = findViewById(R.id.Email);
        userpassword = findViewById(R.id.password);
        userconfirmPass = findViewById(R.id.confpassword);
        signup = findViewById(R.id.signBtn);
        loginRedirection = findViewById(R.id.already);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String email = useremail.getText().toString().trim();
                String pass = userpassword.getText().toString().trim();
                String confpass = userconfirmPass.getText().toString();
                if(email.isEmpty()){
                    useremail.setError("Must enter your email..");
                }
                if(pass.isEmpty()) {
                    userpassword.setError("Must enter your password..");
                }
                 if (pass != confpass) {
                     userconfirmPass.setError("don't matches");
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"SignUp Successfuly",Toast.LENGTH_LONG).show();
                               // Navigation.findNavController(requireView()).navigate(R.id.action_signUpFragment_to_loginFragment);
                                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(SignUpActivity.this,"SignUp Failed.."+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });

        loginRedirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}