package com.example.renttools.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.renttools.R;
import com.example.renttools.model.Tool;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddEditToolActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String toolId;

    private TextInputLayout manufacturerInput, modelInput, descriptionInput, priceInput;
    private Button addToolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool);

        toolbar = findViewById(R.id.main_toolbar_add_tool);
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

        setValuesOnEdit();

    }

    private void setValuesOnEdit() {
        Bundle b = getIntent().getExtras();
        boolean isEdit = b.getBoolean("isEdit");
        if (isEdit) {
            manufacturerInput.getEditText().setText(b.getString("manufacturer"));
            modelInput.getEditText().setText(b.getString("model"));
            descriptionInput.getEditText().setText(b.getString("description"));
            priceInput.getEditText().setText(b.getString("price"));
            toolId = b.getString("toolId");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_add_tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(AddEditToolActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
        }
        return true;
    }

    public void writeToolToDatabase(Tool tool) {
        Toast.makeText(AddEditToolActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        if (tool.getToolId() == null) {
            String uuid = UUID.randomUUID().toString();
            tool.setToolId(uuid);
        }
        mDatabase.child("Tools").child(tool.getToolId()).setValue(tool);

    }

    public void onClickSaveTool(View view) {

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
        Tool tool = new Tool(manuf, model, descr, price);
        tool.setUserId(mAuth.getCurrentUser().getUid());
        tool.setToolId(toolId);
        writeToolToDatabase(tool);
        startActivity(new Intent(AddEditToolActivity.this, ToolsActivity.class));
    }
}