package com.example.ezhealth;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText inputEmail,pass1,fullname;
    Button btnRegister;

    FirebaseDatabase fDatabase;
    DatabaseReference dRef;
    String password;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        inputEmail=findViewById(R.id.email_input_reg);
        pass1=findViewById(R.id.pass_input_reg);
        fullname=findViewById(R.id.username_reg);
        btnRegister=findViewById(R.id.register_btn);


        fDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        dRef = fDatabase.getReference().child("Users");


        btnRegister.setOnClickListener(v -> {

            String uemail=inputEmail.getText().toString();
            String password1=pass1.getText().toString();
            String fname = fullname.getText().toString();

            if (fname.isEmpty()){
                Toast.makeText(SignUp.this, "Full Name is required", Toast.LENGTH_SHORT).show();
                fullname.requestFocus();
                return;
            }
            else if (uemail.isEmpty()){
                Toast.makeText(SignUp.this, "Email is required", Toast.LENGTH_SHORT).show();
                inputEmail.requestFocus();
                return;
            }
            else if (password1.isEmpty()) {
                Toast.makeText(SignUp.this, "Password is required", Toast.LENGTH_SHORT).show();
                pass1.requestFocus();
                return;
            }
            else if (password1.length()<6){
                Toast.makeText(SignUp.this, "Password Must Be More or Equal than 6 Characters", Toast.LENGTH_SHORT).show();
                pass1.requestFocus();
                return;
            }
            else {
                password = password1;
            }


            mAuth.createUserWithEmailAndPassword(uemail,password1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(fname, uemail);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(SignUp.this, "User created succesfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(SignUp.this, "Fail! Try again!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Fail to register! Try again!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });

            if (fname!=null ||uemail!=null){
            }

        });

    }


    public void loginMethod (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}