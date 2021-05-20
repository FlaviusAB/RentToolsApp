package com.example.renttools.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renttools.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ToolInformationActivity extends AppCompatActivity {

    private TextView manufText, modelText, descrText, priceText;
    private ImageView image;
    private Toolbar toolbar;
    private Button editButton, deleteButton;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_information);
        image = findViewById(R.id.imageViewTool);

        manufText = findViewById(R.id.textViewManufacturer);
        modelText = findViewById(R.id.textViewModel);
        descrText = findViewById(R.id.textViewDescription);
        priceText= findViewById(R.id.textViewPrice);
        editButton = findViewById(R.id.buttonEditTool);
        deleteButton = findViewById(R.id.buttonDeleteTool);


        toolbar = findViewById(R.id.main_toolbar_my_tools);
        setSupportActionBar(toolbar);

        image.setImageResource(R.drawable.image_not_available);

        Bundle b = getIntent().getExtras();
        String manufacturer = b.getString("manufacturer");
        String model = b.getString("model");
        String description = b.getString("description");
        Double price = b.getDouble("price");
        String activity = b.getString("activity");
        uId = b.getString("toolId");

        if(activity.equals("ToolsActivity"))
        {
            editButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

        manufText.setText(manufacturer);
        modelText.setText(model);
        descrText.setText(description);
        priceText.setText(price.toString()+" kr/day");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://renttools-b4395-default-rtdb.europe-west1.firebasedatabase.app");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_app_bar_tool_info, menu);
        return true;
    }

    public void onClickEdit(View view) {

    }

    public void onClickDelete(View view) {
        database.getReference().child("Tools").child(uId).removeValue();
        Toast.makeText(this,"Succesfuly removed",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ToolsActivity.class));
    }
}