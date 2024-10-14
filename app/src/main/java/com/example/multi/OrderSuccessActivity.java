package com.example.multi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success); // Make sure this layout exists

        TextView successMessage = findViewById(R.id.success_message);
        successMessage.setText("Your order has been placed successfully!");

        Button backToShopButton = findViewById(R.id.back_to_shop_button);
        backToShopButton.setOnClickListener(v -> finish());
    }
}
