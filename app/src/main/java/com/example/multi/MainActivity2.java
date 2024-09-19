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

    // Array of drawable resource IDs for images
    int[] images = {
            R.drawable.phones,    // shoes.jpg
            R.drawable.laptops,    // watch.jpg
            R.drawable.cameras,   // AirPods.jpg
            R.drawable.headphones,
            R.drawable.smartwatch

    };

    // Array of product names
    String[] productNames = {
            "Mobile ",   // Product name for shoes
            "Laptops",   // Product name for watch
            "Cameras", // Product name for AirPods
            "Headphones",
            "Smartwatch"


    };
    String[]Fashion={
            "Men’s Clothing",
    "Women’s Clothing",
    "Footwear",
            "Watches",
    "Bags & Wallet"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonCategoriesDrawerToggle = findViewById(R.id.buttonCategoriesDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        buttonCategoriesDrawerToggle.setOnClickListener(view -> drawerLayout.open());
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                Toast.makeText(MainActivity2.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
            drawerLayout.close();
            return false;
        });




        // Get the parent layout that contains the card views
        LinearLayout cardLayout = findViewById(R.id.card_container);

        // Loop through each card and set the corresponding image and product name

        for (int i = 0; i < cardLayout.getChildCount(); i++) {
            View view = cardLayout.getChildAt(i);

            // Find the ImageView and TextView inside the included layout
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.product_name);  // Make sure to give the TextView an id

            // Set the image and product name for each card view
            if (i < images.length) {
                imageView.setImageResource(images[i]);
                textView.setText(productNames[i]);
            }
        }

    }
}

