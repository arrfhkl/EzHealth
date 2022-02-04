package com.example.ezhealth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private  Button button;
    EditText e1, e2;
    EditText inputEmail, inputPassword;
    String email, password;
    Button btnLogin, btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail=findViewById(R.id.email_input_log);
        inputPassword=findViewById(R.id.pass_input_log);
        btnLogin=findViewById(R.id.signin_btn);
        mAuth = FirebaseAuth.getInstance();
        btnSignUp=findViewById(R.id.signup_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }



    private void checkLogin(){

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (email.isEmpty()||password.isEmpty()){
            Toast.makeText(MainActivity.this, "email or password cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent i = new Intent(getApplicationContext(),RahsiaActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Login Unsuccessful, Email or Password ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    public void signUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}