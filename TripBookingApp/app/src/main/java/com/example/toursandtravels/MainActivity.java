package com.example.toursandtravels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button logIn,signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check login state before setting the content view
        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            // If logged in, go directly to the main content
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            finish(); // Prevent user from going back to the login screen
            return; // Skip the rest of the setup
        }

        // If not logged in, show the login/signup page
        setContentView(R.layout.activity_main);

        logIn = findViewById(R.id.logIn);
        signUp = findViewById(R.id.signUp);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, signInPage.class);
                startActivity(intent1);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, registration_page.class);
                startActivity(intent2);
            }
        });
    }
}
