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

public class FictionBooksActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;
    GridLayout gridLayout;

    // Arrays to store fiction book images and names
    int[] fictionBookImages = {
            R.drawable.fiction_book1,
            R.drawable.fiction_book2,
            R.drawable.fiction_book3,
            R.drawable.fiction_book4,
            R.drawable.fiction_book5,
            R.drawable.fiction_book6,
            R.drawable.fiction_book7,
            R.drawable.fiction_book8,
            R.drawable.fiction_book9,
            R.drawable.fiction_book10
    };

    String[] fictionBookNames = {
            "To Kill a Mockingbird - Harper Lee",
            "1984 - George Orwell",
            "The Great Gatsby - F. Scott Fitzgerald",
            "The Catcher in the Rye - J.D. Salinger",
            "Brave New World - Aldous Huxley",
            "The Lord of the Rings - J.R.R. Tolkien",
            "The Hobbit - J.R.R. Tolkien",
            "Moby Dick - Herman Melville",
            "The Odyssey - Homer",
            "Crime and Punishment - Fyodor Dostoevsky"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiction_books);

        // Initialize views
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        gridLayout = findViewById(R.id.main);

        // Set up button to navigate back to the Categories page (MainActivity2)
        buttonCategoriesDrawerToggle.setOnClickListener(view -> {
            Intent intent = new Intent(FictionBooksActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up grid items for fiction books
        setupGridItems(gridLayout, fictionBookImages, fictionBookNames);
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
                final String productPrice = "$15.99"; // Replace with actual price
                final String productDescription = "Fiction book: " + productName;

                cardView.setOnClickListener(v -> {
                    // Start the ProductActivity
                    Intent intent = new Intent(FictionBooksActivity.this, ProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_image_res_id", productImageResId);
                    intent.putExtra("product_price", productPrice);
                    intent.putExtra("product_description", productDescription);
                    startActivity(intent);

                    // Optionally show a Toast message
                    Toast.makeText(FictionBooksActivity.this, productName + " Clicked", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}
