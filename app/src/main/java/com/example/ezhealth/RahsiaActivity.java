package com.example.ezhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RahsiaActivity extends AppCompatActivity {
    private Button button;
    Button btnUserProfile;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rahsia);

        Logout = findViewById(R.id.lgout_btn);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(RahsiaActivity.this, "Succesfully Logout Account", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RahsiaActivity.this,MainActivity.class));
            }
        });
    }


    public void userProfile(View view){
        Intent intent = new Intent(this, ProfileUser.class);
        startActivity(intent);
    }

    public void mapActivity(View view){
        Intent intent = new Intent(this, MapsActivity2.class);
        startActivity(intent);
    }

    public void aboutUs(View view){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
}

