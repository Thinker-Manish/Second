package com.example.multi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText editTextEmail, editTextUsername, editTextPassword;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);  // Ensure the correct layout is referenced (activity_signup.xml)

        // Initialize UI components
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        // Handle Sign-Up button click event
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Validate inputs
                if (validateInputs(email, username, password)) {
                    // Show sign-up success message
                    Toast.makeText(Signup.this, "Sign-Up Successful", Toast.LENGTH_SHORT).show();

                    // Redirect to a new activity (e.g., Home or Login Activity)
                    Intent intent = new Intent(Signup.this, LoginActivity.class);  // Example: Navigate to LoginActivity
                    startActivity(intent);
                } else {
                    // Show error if inputs are invalid
                    Toast.makeText(Signup.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validate inputs method
    private boolean validateInputs(String email, String username, String password) {
        return !email.isEmpty() && !username.isEmpty() && !password.isEmpty();
    }
}
