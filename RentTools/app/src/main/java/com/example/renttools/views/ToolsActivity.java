package com.example.renttools.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.renttools.R;
import com.example.renttools.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class ToolsActivity extends AppCompatActivity {
    private static final String TAG = "ToolsActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        initImageBitmaps();
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
}