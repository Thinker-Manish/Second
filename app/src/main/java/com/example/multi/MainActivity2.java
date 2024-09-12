package com.example.multi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;

public class MainActivity2 extends AppCompatActivity {

    // Array of drawable resource IDs for images
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
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

