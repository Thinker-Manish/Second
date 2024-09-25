package com.example.multi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ClothingActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    int[] clothingImages = {
            R.drawable.shirt,
            R.drawable.jeans,
            R.drawable.jacket,
            R.drawable.dress,
            R.drawable.sweater,
            R.drawable.suit,

            R.drawable.shorts,
            R.drawable.trekpants,
            R.drawable.hat,
            R.drawable.socks,
    };

    String[] clothingNames = {
            "Shirt",
            "Jeans",
            "Jacket",
            "Dress",
            "Sweater",
            "Suit",

            "Shorts",
            "Trek Pants",
            "Hat",
            "Socks"
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main); // Match ID with XML

        // Set up button to navigate back to MainActivity2 (Categories page)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(ClothingActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items
        setupGridItems(gridLayout, clothingImages, clothingNames);
    }

    // Method to set up grid items with images and names
    private void setupGridItems(GridLayout gridLayout, int[] clothingImages, String[] clothingNames) {
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View cardView = gridLayout.getChildAt(i);

            // Find ImageView and TextView in the included card layout
            ImageView imageView = cardView.findViewById(R.id.imageView);
            TextView textView = cardView.findViewById(R.id.product_name);

            // Check if views are not null and within bounds of the array
            if (i < clothingImages.length && imageView != null && textView != null) {
                // Set image and text for each grid item
                imageView.setImageResource(clothingImages[i]);
                textView.setText(clothingNames[i]);

                // Set click listener for each card view
                final String productName = clothingNames[i];
                final int productImageResId = clothingImages[i];
                final String productPrice = "$49.99"; // Replace with actual price
                final String productDescription = "Description for " + productName;

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(ClothingActivity.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(ClothingActivity.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
