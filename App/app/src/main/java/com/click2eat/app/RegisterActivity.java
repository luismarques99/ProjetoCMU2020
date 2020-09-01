package com.click2eat.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";

    //EditTexts
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPasswordConfirmationField;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Edit texts
        mEmailField = findViewById(R.id.email);
        mPasswordField = findViewById(R.id.password);
        mPasswordConfirmationField = findViewById(R.id.password_confirmation);

        // Buttons
        findViewById(R.id.register_button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        String passwordConfirmation = mPasswordConfirmationField.getText().toString();
        if (TextUtils.isEmpty(passwordConfirmation)) {
            mPasswordConfirmationField.setError("Required.");
            valid = false;
        } else {
            mPasswordConfirmationField.setError(null);
        }

        if (!password.equals(passwordConfirmation)) {
            displayPasswordsMustMatchToast();
            valid = false;
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.register_button) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void displayPasswordsMustMatchToast() {
        String text = "Passwords must match!";
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}