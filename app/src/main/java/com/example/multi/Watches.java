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

public class Watches extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    // Array for shoe images
    int[] shoeImages = {
            R.drawable.rolexx,
            R.drawable.tag_heuer,
            R.drawable.omega,
            R.drawable.cartier,
            R.drawable.breitling,
            R.drawable.patek_philippe,
            R.drawable.tissot,
            R.drawable.casio,
            R.drawable.fossil,
            R.drawable.longiness
    };

    // Array for shoe names
    String[] shoeNames = {
            "Rolex",
            "Tag Heuer",
            "Omega",
            "Cartier",
            "Breitling",
            "Patek Philippe",
            "Tissot",
            "Casio",
            "Fossil",
            "Longines"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watches); // Replace with your actual layout

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main); // Match ID with XML

        // Set up button to navigate back to MainActivity2 (Categories page)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(Watches.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items for shoes
        setupGridItems(gridLayout, shoeImages, shoeNames);
    }

    // Method to set up grid items with images and names
    private void setupGridItems(GridLayout gridLayout, int[] shoeImages, String[] shoeNames) {
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View cardView = gridLayout.getChildAt(i);

            // Find ImageView and TextView in the included card layout
            ImageView imageView = cardView.findViewById(R.id.imageView);
            TextView textView = cardView.findViewById(R.id.product_name);

            // Check if views are not null and within bounds of the array
            if (i < shoeImages.length && imageView != null && textView != null) {
                // Set image and text for each grid item
                imageView.setImageResource(shoeImages[i]);
                textView.setText(shoeNames[i]);

                // Set click listener for each card view
                final String productName = shoeNames[i];
                final int productImageResId = shoeImages[i]; // Assuming you want to pass the image resource ID
                final String productPrice = "$199.99"; // Replace this with actual price
                final String productDescription = "Description for " + productName; // Replace with actual description

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(Watches.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(Watches.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
