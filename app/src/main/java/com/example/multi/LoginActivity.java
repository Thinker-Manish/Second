package com.example.multi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button buttonGoToSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Ensure you're referencing your login layout

        // Initialize the button
        buttonGoToSignup = findViewById(R.id.buttonGoToSignup);

        // Set up the click listener to go to the Signup activity
        buttonGoToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the Signup Activity
                Intent intent = new Intent(LoginActivity.this, Signup.class);
                startActivity(intent);  // This will navigate to the Signup Activity
            }
        });
    }
}
