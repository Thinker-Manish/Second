package com.example.multi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    private static final String PREF_NAME = "CartPrefs";
    private static final String CART_ITEMS_KEY = "cart_items";

    private TextView totalPriceTextView;
    private Button checkoutButton;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);

        totalPriceTextView = findViewById(R.id.total_price);  // Updated ID
        checkoutButton = findViewById(R.id.checkout_button);  // Updated ID

        cartItems = loadCartItems();

        displayCartItems();
        updateTotalPrice();

        checkoutButton.setOnClickListener(v -> {
            Toast.makeText(this, "Proceeding to checkout...", Toast.LENGTH_SHORT).show();
            // Here you could clear the cart after checkout
            // clearCart();
        });
    }

    private List<CartItem> loadCartItems() {
        List<CartItem> items = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String cartItemsJson = prefs.getString(CART_ITEMS_KEY, "[]");

        try {
            JSONArray cartItemsArray = new JSONArray(cartItemsJson);
            for (int i = 0; i < cartItemsArray.length(); i++) {
                JSONObject item = cartItemsArray.getJSONObject(i);
                String name = item.getString("name");
                double price = Double.parseDouble(item.getString("price").replace("$", ""));
                int imageResId = item.optInt("imageResId", -1);  // Retrieve image resource ID

                items.add(new CartItem(name, price, imageResId));
            }
            Log.d(TAG, "Loaded " + items.size() + " items from cart");
        } catch (JSONException e) {
            Log.e(TAG, "Error loading cart items", e);
        }

        return items;
    }

    private void displayCartItems() {
        LinearLayout cartItemsLayout = findViewById(R.id.cart_items_layout);  // Updated ID
        cartItemsLayout.removeAllViews();  // Clear previous items

        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            View cartItemView = getLayoutInflater().inflate(R.layout.cart_item_view, null);

            TextView nameTextView = cartItemView.findViewById(R.id.product_name);  // Updated ID
            TextView priceTextView = cartItemView.findViewById(R.id.product_price);  // Updated ID
            ImageView productImageView = cartItemView.findViewById(R.id.product_image);  // Updated ID
            Button removeButton = cartItemView.findViewById(R.id.remove_button);  // New Remove Button

            // Set the item data
            nameTextView.setText(item.getName());
            priceTextView.setText(String.format("$%.2f", item.getPrice()));

            // Set the image resource
            if (item.getImageResId() != -1) {
                productImageView.setImageResource(item.getImageResId());  // Display product image
            } else {
                // Set a placeholder image if no image resource is available
                productImageView.setImageResource(R.drawable.people);
            }

            // Add functionality to the remove button
            final int index = i;
            removeButton.setOnClickListener(v -> {
                removeCartItem(index);
            });

            // Add the item view to the cart layout
            cartItemsLayout.addView(cartItemView);
        }
    }

    private void removeCartItem(int index) {
        cartItems.remove(index);  // Remove the item from the list
        saveCartItems();  // Save the updated cart to preferences
        displayCartItems();  // Refresh the cart display
        updateTotalPrice();  // Update the total price
    }

    private void saveCartItems() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray cartItemsArray = new JSONArray();

        try {
            for (CartItem item : cartItems) {
                JSONObject itemJson = new JSONObject();
                itemJson.put("name", item.getName());
                itemJson.put("price", String.format("$%.2f", item.getPrice()));
                itemJson.put("imageResId", item.getImageResId());  // Save image resource ID
                cartItemsArray.put(itemJson);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error saving cart items", e);
        }

        editor.putString(CART_ITEMS_KEY, cartItemsArray.toString());
        editor.apply();
    }

    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        totalPriceTextView.setText(String.format("Total: $%.2f", total));  // Updated ID
    }

    private void clearCart() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CART_ITEMS_KEY);
        editor.apply();
        cartItems.clear();
        displayCartItems();
        updateTotalPrice();
    }

    // Simple class to represent a cart item
    private static class CartItem {
        private String name;
        private double price;
        private int imageResId;  // New field for image resource ID

        public CartItem(String name, double price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getImageResId() { return imageResId; }  // New getter for imageResId
    }
}
