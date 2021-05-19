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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ToolsActivity extends AppCompatActivity  {
    private static final String TAG = "ToolsActivity";

    private  ArrayList<String> mManufacturerList = new ArrayList<>();
    private  ArrayList<String> mModelList = new ArrayList<>();
    private  ArrayList<String> mPriceList = new ArrayList<>();

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
        //Get datasnapshot at your "users" root node
        DatabaseReference ref = database.getReference().child("Tools");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of tools in datasnapshot
                        collectTools((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void collectTools(Map<String, Object> tools) {

        for (Map.Entry<String, Object> entry : tools.entrySet()){

            Map singleTool = (Map) entry.getValue();

            mManufacturerList.add((String) singleTool.get("manufacturer"));
            mModelList.add((String) singleTool.get("model"));
            String p = String.valueOf((Long) singleTool.get("pricePerDay"));
            mPriceList.add(p);
        }
        System.out.println(mManufacturerList.toString());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ToolsActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
        }
        return true;
    }

    private void initImageBitmaps(){
        mImageUrls.add("https://i.redd.it/9lsqbiv0icz61.jpg");
        mImageUrls.add("https://i.redd.it/4qjsodgt5iy61.jpg");
        mImageUrls.add("https://i.redd.it/qakqqh1qd8z61.jpg");

        getAllToolsFromFirebase();

        /*mImageUrls.add("https://i.redd.it/jz58dpd593z61.jpg");
        mNames.add("DeWalt flexvolt");

        mImageUrls.add("https://i.redd.it/kpoqzobe95z61.jpg");
        mNames.add("Bostitch");

        mImageUrls.add("https://preview.redd.it/4agyagv51ly61.jpg?width=4032&format=pjpg&auto=webp&s=e87763534b2fb3b03be13b2977aeaaa5a8a82ada");
        mNames.add("Bostitch");

        mImageUrls.add("https://i.redd.it/9lsqbiv0icz61.jpg");
        mNames.add("Aircat impact wrench");

        mImageUrls.add("https://i.redd.it/4qjsodgt5iy61.jpg");
        mNames.add("John Deere s130 lawn mower");

        mImageUrls.add("https://i.redd.it/qakqqh1qd8z61.jpg");
        mNames.add("Polish machine");

        mImageUrls.add("https://i.redd.it/jz58dpd593z61.jpg");
        mNames.add("DeWalt flexvolt");

        mImageUrls.add("https://i.redd.it/kpoqzobe95z61.jpg");
        mNames.add("Bostitch");

        mImageUrls.add("https://preview.redd.it/4agyagv51ly61.jpg?width=4032&format=pjpg&auto=webp&s=e87763534b2fb3b03be13b2977aeaaa5a8a82ada");
        mNames.add("Bostitch");*/

        initRecyclerView();


    }

    private void initRecyclerView()
    {
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycleViewTools);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mManufacturerList,mModelList,mPriceList,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void onRentATool(View view) {
        startActivity(new Intent(this, AddToolActivity.class));
    }
}