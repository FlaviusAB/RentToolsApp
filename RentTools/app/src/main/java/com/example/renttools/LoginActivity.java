package com.example.renttools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_login);


    }

    public void onClickSignUp(View view) {
        startActivity(new Intent(this, SignUpUser.class));
        finish();
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
