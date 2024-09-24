package com.example.multi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page); // Ensure this layout matches your XML

        // Initialize views
        ImageView productImage = findViewById(R.id.product_image);
        TextView productTitle = findViewById(R.id.product_title);
        TextView productPrice = findViewById(R.id.product_price);
        TextView productDescription = findViewById(R.id.product_description);

        // Get data from Intent
        String productName = getIntent().getStringExtra("product_name");
        int productImageResId = getIntent().getIntExtra("product_image_res_id", -1);
        String productPriceValue = getIntent().getStringExtra("product_price");
        String productDescriptionValue = getIntent().getStringExtra("product_description");

        // Set data to views
        productTitle.setText(productName);
        productImage.setImageResource(productImageResId);
        productPrice.setText(productPriceValue);
        productDescription.setText(productDescriptionValue);
    }
}
