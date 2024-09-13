package com.example.multi;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout) ;
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);


        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.categories) {
                    Toast.makeText(MainActivity.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
                    // Use MainActivity.this as the context
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
                drawerLayout.close();
                return false;
            }
        });
//        hi
    }

}
