package com.example.multi;

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

public class Bags extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    int[] bagImages = {
            R.drawable.bag1,
            R.drawable.bag2,
            R.drawable.bag3,
            R.drawable.bag4,
            R.drawable.bag5,
            R.drawable.bag6,
            R.drawable.bag7,
            R.drawable.bag8,
            R.drawable.bag9,
            R.drawable.bag10
    };

    String[] bagNames = {
            "Travel Bag",
            "Laptop Bag",
            "Handbag",
            "Backpack",
            "Gym Bag",
            "Messenger Bag",
            "Tote Bag",
            "Duffel Bag",
            "Sling Bag",
            "Briefcase"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bags);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main);

        // Set up button to navigate back to MainActivity2 (Categories page)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(Bags.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items
        setupGridItems(gridLayout, bagImages, bagNames);
    }

    // Method to set up grid items with images and names
    private void setupGridItems(GridLayout gridLayout, int[] bagImages, String[] bagNames) {
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View cardView = gridLayout.getChildAt(i);

            // Find ImageView and TextView in the included card layout
            ImageView imageView = cardView.findViewById(R.id.imageView);
            TextView textView = cardView.findViewById(R.id.product_name);

            // Check if views are not null and within bounds of the array
            if (i < bagImages.length && imageView != null && textView != null) {
                // Set image and text for each grid item
                imageView.setImageResource(bagImages[i]);
                textView.setText(bagNames[i]);

                // Set click listener for each card view
                final String productName = bagNames[i];
                final int productImageResId = bagImages[i];
                final String productPrice = "$299.99"; // Replace with actual price
                final String productDescription = "Description for " + productName;

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(Bags.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(Bags.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
