package com.example.multi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";
    private static final String PREF_NAME = "CartPrefs";
    private static final String CART_ITEMS_KEY = "cart_items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page);

        // Initialize views
        ImageView productImage = findViewById(R.id.product_image);
        TextView productTitle = findViewById(R.id.product_title);
        TextView productPrice = findViewById(R.id.product_price);
        TextView productDescription = findViewById(R.id.product_description);

        // Get data from Intent
        String productName = getIntent().getStringExtra("product_name");
        int productImageResId = getIntent().getIntExtra("product_image_res_id", -1);
        String productPriceValue = getIntent().getStringExtra("product_price");
        String productDescriptionValue = getIntent().getStringExtra("product_description");

        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        addToCartButton.setOnClickListener(v -> {
            Log.d(TAG, "Add to Cart button clicked");
            // Pass the image resource ID along with the other product details
            addToCart(productName, productPriceValue, productImageResId);
            Toast.makeText(this, "Added to cart: " + productName, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ProductActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Set data to views
        productTitle.setText(productName);
        productImage.setImageResource(productImageResId);
        productPrice.setText(productPriceValue);
        productDescription.setText(productDescriptionValue);
    }

    // Updated method to add the image along with the name and price to the cart
    private void addToCart(String name, String price, int imageResId) {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String cartItemsJson = prefs.getString(CART_ITEMS_KEY, "[]");

        try {
            JSONArray cartItems = new JSONArray(cartItemsJson);
            JSONObject newItem = new JSONObject();
            newItem.put("name", name);
            newItem.put("price", price);
            newItem.put("imageResId", imageResId);  // Store the image resource ID in the cart
            cartItems.put(newItem);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(CART_ITEMS_KEY, cartItems.toString());
            editor.apply();

            Log.d(TAG, "Added to cart: " + name + " - " + price + " - Image: " + imageResId);
        } catch (JSONException e) {
            Log.e(TAG, "Error adding item to cart", e);
        }
    }
}
