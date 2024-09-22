package com.example.multi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton buttonCategoriesDrawerToggle;
    NavigationView navigationView;

    // Array of drawable resource IDs for Electronics
    int[] electronicsImages = {
            R.drawable.phones,    // Mobile image
            R.drawable.laptops,    // Laptop image
            R.drawable.cameras,   // Camera image
            R.drawable.headphones,
            R.drawable.smartwatch
    };

    // Array of Electronics product names
    String[] electronicsProductNames = {
            "Mobile",
            "Laptops",
            "Cameras",
            "Headphones",
            "Smartwatch"
    };

    // Array of drawable resource IDs for Fashion
    int[] fashionImages = {
            R.drawable.men_clothing,   // Men's clothing image

            R.drawable.shoes,        // Footwear image
            R.drawable.watch,         // Watches image
            R.drawable.bags_wallets     // Bags and Wallets image
    };

    // Array of Fashion product names
    String[] fashionProductNames = {
            "Clothing",

            "Footwears",
            "Watches",
            "Bags"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Drawer and navigation setup
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        buttonCategoriesDrawerToggle.setOnClickListener(view -> drawerLayout.open());
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                Toast.makeText(MainActivity2.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
            drawerLayout.close();
            return false;
        });

        // Set up Electronics card views
        setupCardViews(R.id.card_container, electronicsImages, electronicsProductNames);

        // Set up Fashion card views
        setupCardViews(R.id.card_container_popular, fashionImages, fashionProductNames);
    }

    // Helper method to set up card views
    private void setupCardViews(int containerId, int[] images, String[] productNames) {
        LinearLayout cardLayout = findViewById(containerId);

        // Loop through each card and set the corresponding image and product name
        for (int i = 0; i < cardLayout.getChildCount(); i++) {
            View view = cardLayout.getChildAt(i);

            // Find the ImageView and TextView inside the included layout
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.product_name);

            // Set the image and product name for each card view
            if (i < images.length) {
                imageView.setImageResource(images[i]);
                textView.setText(productNames[i]);
            }
        }
    }
}
