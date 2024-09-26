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

public class PoliticalBooksActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    // Arrays to store political book images and names
    int[] politicalBookImages = {
            R.drawable.political_book1,  // Updated drawable names
            R.drawable.political_book2,
            R.drawable.political_book3,
            R.drawable.political_book4,
            R.drawable.political_book5,
            R.drawable.political_book6,
            R.drawable.political_book7,
            R.drawable.political_book8
    };

    String[] politicalBookNames = {
            "The Communist Manifesto - Karl Marx",
            "The Road to Serfdom - Friedrich Hayek",
            "Leviathan - Thomas Hobbes",
            "On Liberty - John Stuart Mill",
            "The Federalist Papers - Alexander Hamilton",
            "Democracy in America - Alexis de Tocqueville",
            "The Origins of Totalitarianism - Hannah Arendt",
            "Capitalism and Freedom - Milton Friedman"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_political_books);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main);

        // Set up button to navigate back to the Categories page (MainActivity2)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(PoliticalBooksActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items for political books
        setupGridItems(gridLayout, politicalBookImages, politicalBookNames);
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
                final String productPrice = "$19.99"; // Replace with actual price
                final String productDescription = "Political book: " + productName;

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(PoliticalBooksActivity.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(PoliticalBooksActivity.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}

