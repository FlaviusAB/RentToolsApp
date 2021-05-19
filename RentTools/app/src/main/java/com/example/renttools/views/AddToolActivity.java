package com.example.renttools.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.renttools.R;
import com.example.renttools.model.Tool;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToolActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private TextInputLayout manufacturerInput, modelInput, descriptionInput, priceInput;
    private Button addToolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.add_tool_string);
        addToolButton = findViewById(R.id.buttonRentTool);

        manufacturerInput = findViewById(R.id.manufacturerTextField);
        modelInput = findViewById(R.id.modelTextField);
        descriptionInput = findViewById(R.id.descriptionTextField);
        priceInput = findViewById(R.id.priceTextField);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://renttools-b4395-default-rtdb.europe-west1.firebasedatabase.app");
        mDatabase = database.getReference();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }
    public void writeToolToDatabase(Tool tool) {
        Toast.makeText(AddToolActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        mDatabase.child("Tools").child(mAuth.getCurrentUser().getUid()).setValue(tool);

    }
    public void onClickAddTool(View view) {

        String manuf = manufacturerInput.getEditText().getText().toString();
        String model = modelInput.getEditText().getText().toString();
        String descr = descriptionInput.getEditText().getText().toString();
        Double price = Double.parseDouble(priceInput.getEditText().getText().toString());


        if (manuf.isEmpty()) {
            manufacturerInput.setError("Field required, \n type 'unknown' if not known ");
            manufacturerInput.requestFocus();
            return;
        }
        if (model.isEmpty()) {
            modelInput.setError("Field required, \n type 'unknown' if not known ");
            modelInput.requestFocus();
            return;
        }
        if (descr.isEmpty()) {
            descriptionInput.setError("Type description of tool");
            descriptionInput.requestFocus();
            return;

        }
        if (priceInput.getEditText().getText().toString().isEmpty()) {
            priceInput.setError("Please provide a price");
            priceInput.requestFocus();
            return;
        }
        Tool tool = new Tool(manuf,model,descr,price);

        writeToolToDatabase(tool);
    }
}