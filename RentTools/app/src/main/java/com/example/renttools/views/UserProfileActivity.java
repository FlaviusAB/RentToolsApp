package com.example.renttools.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.renttools.R;
import com.example.renttools.model.Tool;
import com.example.renttools.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {

    private TextView emailText, phoneText, locationText, usernameText;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        emailText = findViewById(R.id.email);
        phoneText = findViewById(R.id.phone);
        locationText = findViewById(R.id.location);
        usernameText = findViewById(R.id.username);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usr = mAuth.getCurrentUser();

        emailText.setText("Email: "+usr.getEmail());

        database = FirebaseDatabase.getInstance("https://renttools-b4395-default-rtdb.europe-west1.firebasedatabase.app");
        getUserDetails(usr);
    }

    private void getUserDetails(FirebaseUser usr) {
        DatabaseReference ref = database.getReference().child("Users").child(usr.getUid());
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GenericTypeIndicator<User> t = new GenericTypeIndicator<User>() {};
                        User localUser = dataSnapshot.getValue(t);
                        phoneText.setText("Phone: "+localUser.getPhoneNumber());
                        locationText.setText("Location:" + localUser.getPhoneNumber());
                        usernameText.setText(localUser.getName());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }
}