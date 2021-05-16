package com.example.renttools.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.renttools.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmailAddress, editTextPassword;

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_login);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onClickSignUp(View view) {
        startActivity(new Intent(this, SignUpUser.class));
        finish();
    }

    public void onClickLogin(View view) {
        startActivity(new Intent(this, ToolsActivity.class));
    }

    private void loginUser() {
        String email = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (email.isEmpty()) {
            editTextEmailAddress.setError("Email is required!");
            editTextEmailAddress.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailAddress.setError("Please provide a valid email");
            editTextEmailAddress.requestFocus();
            return;
        }
    }



   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK)
            goToMainActivity();
        else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }
    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void onClickSignUp(View view) {
        startActivity(new Intent(this, SignUpUser.class));
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.icon_tools)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/

}
