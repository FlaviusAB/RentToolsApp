package com.example.renttools.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ToolsActivity extends AppCompatActivity {
    private static final String TAG = "ToolsActivity";
    private final ArrayList<String> mNames = new ArrayList<>();
    private final ArrayList<String> mImageUrls = new ArrayList<>();

    MaterialToolbar toolbar;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    Tool abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.topAppBar);

        abc = new Tool("Yamaha","zt100","lawnmower",100);

        setContentView(R.layout.activity_tools);
        initImageBitmaps();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }
    private void initImageBitmaps(){

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
        mNames.add("Bostitch");

        initRecyclerView();
        initRecyclerView();

    }

    private void initRecyclerView()
    {
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycleViewTools);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ToolsActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                return  super.onOptionsItemSelected(item);

        }
        return true;
    }

    public void writeToolToUser(Tool tool) {
        Toast.makeText(ToolsActivity.this, "aaarived", Toast.LENGTH_SHORT).show();
        mDatabase.child("Tools").setValue("dasdasd");

    }


    public void onClickAddTool(View view) {
    writeToolToUser(abc);
    }
}