package com.example.multi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView buttonDrawerToggle;
    NavigationView navigationView;

    // Array of image resources
    int[] images = {
            R.drawable.shoes,    // shoes.jpg
            R.drawable.watch,    // watch.jpg
            R.drawable.airpods   // AirPods.jpg
    };

    // Array of product names
    String[] productNames = {
            "Shoes",   // Product name for shoes
            "Watch",   // Product name for watch
            "AirPods"  // Product name for AirPods
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        buttonDrawerToggle.setOnClickListener(view -> drawerLayout.open());

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.categories) {
                Toast.makeText(MainActivity.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
            drawerLayout.close();
            return false;
        });

        // Set up featured product cards
        setUpProductCards(R.id.card_container, images, productNames);

        // Set up most popular product cards (you can pass different arrays if needed)
        setUpProductCards(R.id.card_container_popular, images, productNames);
    }

    // Function to set up cards dynamically
    private void setUpProductCards(int containerId, int[] imageArray, String[] nameArray) {
        // Find the layout where the cards are to be added
        LinearLayout cardContainer = findViewById(containerId);

        // Remove any existing views
        cardContainer.removeAllViews();

        // Loop through the arrays and add card views
        for (int i = 0; i < imageArray.length; i++) {
            // Inflate the card layout
            View cardView = LayoutInflater.from(this).inflate(R.layout.card_item, cardContainer, false);

            // Set the image and product name dynamically
            ImageView productImage = cardView.findViewById(R.id.imageView);
            TextView productName = cardView.findViewById(R.id.product_name);

            productImage.setImageResource(imageArray[i]);
            productName.setText(nameArray[i]);

            // Add the card view to the container
            cardContainer.addView(cardView);
        }
    }
}
