
package com.example.renttools.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.renttools.R;
import com.example.renttools.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpUser extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private ProgressBar progressBar;
    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone, editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        mAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPhone = findViewById(R.id.phone);
        editTextLocation = findViewById(R.id.location);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpUser.this, LoginActivity.class));
    }

    public void onClickSignUp(View view) {

        registerUser();

    }

    public void registerUser() {

        String email = editTextEmail.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Name is required!");
            editTextName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min 6 characters for password");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.VISIBLE);
                    if (task.isSuccessful()) {
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        User user = new User(uid, email, name, phone, location);
                        FirebaseDatabase.getInstance("https://renttools-b4395-default-rtdb.europe-west1.firebasedatabase.app")
                                .getReference("Users")
                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {

                                Toast.makeText(SignUpUser.this, "You have been registered successfully", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                SignUpUser.this.startActivity(new Intent(SignUpUser.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpUser.this, "Failed to register! Tray again!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(SignUpUser.this, "Failed to register! Tray again!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}