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

public class PsychologyBooksActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    // Arrays to store book images and names
    int[] psychologyBookImages = {
            R.drawable.freud_book1,
            R.drawable.freud_book2,
            R.drawable.jung_book1,
            R.drawable.jung_book2,
            R.drawable.rogers_book1,
            R.drawable.rogers_book2,
            R.drawable.peterson_book1,
            R.drawable.peterson_book2,
            R.drawable.freud_book3,
            R.drawable.jung_book3
    };

    String[] psychologyBookNames = {
            "The Interpretation of Dreams - Sigmund Freud",
            "Civilization and Its Discontents - Sigmund Freud",
            "The Archetypes and The Collective Unconscious - Carl Jung",
            "Man and His Symbols - Carl Jung",
            "On Becoming a Person - Carl Rogers",
            "A Way of Being - Carl Rogers",
            "12 Rules for Life - Jordan Peterson",
            "Beyond Order - Jordan Peterson",
            "Totem and Taboo - Sigmund Freud",
            "Modern Man in Search of a Soul - Carl Jung"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychology_books);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main);

        // Set up button to navigate back to the Categories page (MainActivity2)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(PsychologyBooksActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items for psychology books
        setupGridItems(gridLayout, psychologyBookImages, psychologyBookNames);
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
                final String productPrice = "$24.99"; // Replace with actual price
                final String productDescription = "Psychology book: " + productName;

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(PsychologyBooksActivity.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(PsychologyBooksActivity.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
