package com.example.ezhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class ProfileUser extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    FirebaseDatabase fDatabase;
    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        fDatabase = FirebaseDatabase.getInstance();
        dRef = fDatabase.getReference().child("Users");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();




        final TextView fullnameTextView = findViewById(R.id.full_name);

        final TextView emailTextView = findViewById(R.id.user_email);



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);

                if(userprofile!=null){
                    String fname = userprofile.fname;

                    String uemail = userprofile.uemail;


                    fullnameTextView.setText(fname);

                    emailTextView.setText(uemail);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileUser.this, "Cannot load the profile", Toast.LENGTH_SHORT).show();
            }
        });


        String user_agent = System.getProperty("http.agent");
        Date dateCurrent = Calendar.getInstance().getTime();
        String dateTime = dateCurrent.toString();
        dRef.child(userID).child("datetime").setValue(dateTime);
        dRef.child(userID).child("useragent").setValue(user_agent);

    }

}