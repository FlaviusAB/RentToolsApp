package com.example.renttools.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renttools.R;

public class ToolInformationActivity extends AppCompatActivity {

    private TextView manufText, modelText, descrText, priceText;
    private ImageView image;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_information);
        image = findViewById(R.id.imageViewTool);

        manufText = findViewById(R.id.textViewManufacturer);
        modelText = findViewById(R.id.textViewModel);
        descrText = findViewById(R.id.textViewDescription);
        priceText= findViewById(R.id.textViewPrice);

        toolbar = findViewById(R.id.main_toolbar_my_tools);
        setSupportActionBar(toolbar);

        image.setImageResource(R.drawable.image_not_available);

        Bundle b = getIntent().getExtras();
        String manufacturer = b.getString("manufacturer");
        String model = b.getString("model");
        String description = b.getString("description");
        Double price = b.getDouble("price");

        manufText.setText(manufacturer);
        modelText.setText(model);
        descrText.setText(description);
        priceText.setText(price.toString()+" kr/day");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_tool_info, menu);
        return true;
    }
}