package com.example.renttools;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpUser extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText editTextName, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        mAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.editTextPersonName);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickSignUp(View view) {

        registerUser();

    }

    public void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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
        if(password.length()<6){
            editTextPassword.setError("Min 6 characters for password");
            editTextPassword.requestFocus();
            return;
        }
    mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    User user = new User(name,email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpUser.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();

                                SignUpUser.this.startActivity(new Intent(SignUpUser.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpUser.this, "Failed to register! Tray again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUpUser.this,"Failed to register! Tray again!", Toast.LENGTH_SHORT).show();
                }
            });

    }

}