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

public class PhilosophyActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    // Arrays to store philosophy book images and names
    int[] philosophyBookImages = {
            R.drawable.philosophy_book1,
            R.drawable.philosophy_book2,
            R.drawable.philosophy_book3,
            R.drawable.philosophy_book4,
            R.drawable.philosophy_book5,
            R.drawable.philosophy_book6,
            R.drawable.philosophy_book7,
            R.drawable.philosophy_book8,
            R.drawable.philosophy_book9,
            R.drawable.philosophy_book110
    };

    String[] philosophyBookNames = {
            "The Republic",
            "Critique of Pure Reason",
            "Meditations",
            "Being and Time",
            "The Phenomenology of Spirit",
            "The Stranger",
            "Thus Spoke Zarathustra",
            "Stoicism",
            "The Prince",
            "Beyond Good and Evil"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_philosophy);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main);

        // Set up button to navigate back to the Categories page (MainActivity2)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(PhilosophyActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items for philosophy books
        setupGridItems(gridLayout, philosophyBookImages, philosophyBookNames);
    }

    // Method to set up grid items with images and names
    private void setupGridItems(GridLayout gridLayout, int[] bookImages, String[] bookNames) {
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View cardView = gridLayout.getChildAt(i);

            // Find ImageView and TextView in the included card layout
            ImageView imageView = cardView.findViewById(R.id.imageView);
            TextView textView = cardView.findViewById(R.id.product_name);

            // Check if views are not null and within bounds of the array
            if (i < bookImages.length && imageView != null && textView != null) {
                // Set image and text for each grid item
                imageView.setImageResource(bookImages[i]);
                textView.setText(bookNames[i]);

                // Set click listener for each card view
                final String productName = bookNames[i];
                final int productImageResId = bookImages[i];
                final String productPrice = "$29.99"; // Replace with actual price
                final String productDescription = "Philosophy book: " + productName;

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(PhilosophyActivity.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(PhilosophyActivity.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}

