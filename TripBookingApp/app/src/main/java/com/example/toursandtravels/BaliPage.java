package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BaliPage extends AppCompatActivity {
    ImageButton back_btn;
    Button book_place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bali_page);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        book_place = (Button) findViewById(R.id.bookplace);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        book_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), book_now2.class);
                intent.putExtra("PLACE_NAME", "Bali");
                startActivity(intent);
            }
        });
    }
}