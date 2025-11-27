package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IslandPage extends AppCompatActivity {
    CardView island1,island2,island3,island4,island5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island_page);
        island1 = (CardView) findViewById(R.id.island1);
        island2 = (CardView) findViewById(R.id.island2);
        island3 = (CardView) findViewById(R.id.island3);
        island4 = (CardView) findViewById(R.id.island4);
        island5 = (CardView) findViewById(R.id.island5);
        island1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IslasSecasPage.class);
                startActivity(intent);
            }
        });
        island2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoraBoraPage.class);
                startActivity(intent);
            }
        });
        island3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LangkawiPage.class);
                startActivity(intent);
            }
        });
        island4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BaliPage.class);
                startActivity(intent);
            }
        });
        island5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhuketPage.class);
                startActivity(intent);
            }
        });
    }
}