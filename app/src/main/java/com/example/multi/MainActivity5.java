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

public class MainActivity5 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    int[] cameraImages = {
            R.drawable.canon,
            R.drawable.nikon,
            R.drawable.sony,
            R.drawable.fujifilm,
            R.drawable.panasonic,
            R.drawable.olympus,
            R.drawable.leica,
            R.drawable.gopro,
            R.drawable.pentax,
            R.drawable.hasselblad
    };

    String[] cameraNames = {
            "Canon",
            "Nikon",
            "Sony",
            "Fujifilm",
            "Panasonic",
            "Olympus",
            "Leica",
            "GoPro",
            "Pentax",
            "Hasselblad"
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main); // Match ID with XML

        // Set up button to navigate back to MainActivity2 (Categories page)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items
        setupGridItems(gridLayout, cameraImages, cameraNames);
    }

    // Method to set up grid items with images and names
    private void setupGridItems(GridLayout gridLayout, int[] cameraImages, String[] cameraNames) {
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View cardView = gridLayout.getChildAt(i);

            // Find ImageView and TextView in the included card layout
            ImageView imageView = cardView.findViewById(R.id.imageView);
            TextView textView = cardView.findViewById(R.id.product_name);

            // Check if views are not null and within bounds of the array
            if (i < cameraImages.length && imageView != null && textView != null) {
                // Set image and text for each grid item
                imageView.setImageResource(cameraImages[i]);
                textView.setText(cameraNames[i]);
                final String productName = cameraNames[i];
                final int productImageResId = cameraImages[i]; // Assuming you want to pass the image resource ID
                final String productPrice = "$999.99"; // Replace this with actual price
                final String productDescription = "Description for " + productName; // Replace with actual description


                // Set click listener for each card view

                cardView.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity5.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);
                    // Show a toast message on product click
                    Toast.makeText(MainActivity5.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
