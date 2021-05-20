package com.example.renttools.views;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.renttools.R;
import com.example.renttools.adapters.RecyclerViewAdapter;

import com.example.renttools.model.Tool;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ToolsActivity extends AppCompatActivity  {
    private static final String TAG = "ToolsActivity";


    private  ArrayList<Tool> mToolList = new ArrayList<>();

    private final ArrayList<String> mImageUrls = new ArrayList<>();
    private Toolbar toolbar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://renttools-b4395-default-rtdb.europe-west1.firebasedatabase.app");
        mDatabase = database.getReference();


        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        initImageBitmaps();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    public void getAllToolsFromFirebase()
    {

        DatabaseReference ref = database.getReference().child("Tools");
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GenericTypeIndicator<Map<String, Tool>> t = new GenericTypeIndicator<Map<String, Tool>>() {};
                        collectTools(dataSnapshot.getValue(t));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void collectTools(Map<String, Tool> tools) {
        mToolList.clear();
        for (Map.Entry<String, Tool> entry : tools.entrySet()){
            mToolList.add(entry.getValue());
        }

        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ToolsActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            case R.id.myTools:
                startActivity(new Intent(this, MyToolsActivity.class));
            case R.id.item_settings:
                startActivity(new Intent(this, SettingsActivity.class));
            case R.id.profile:
                startActivity(new Intent(this, UserProfileActivity.class));
        }
        return true;
    }

    private void initImageBitmaps(){
        mImageUrls.add("https://i.redd.it/9lsqbiv0icz61.jpg");
        getAllToolsFromFirebase();
        initRecyclerView();

    }

    private void initRecyclerView()
    {
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycleViewTools);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mToolList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



}