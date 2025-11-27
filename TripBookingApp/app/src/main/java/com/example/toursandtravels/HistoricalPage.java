package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HistoricalPage extends AppCompatActivity {
    CardView historical1,historical2,historical3,historical4,historical5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_page);
        historical1 = (CardView) findViewById(R.id.historical1);
        historical2 = (CardView) findViewById(R.id.historical2);
        historical3 = (CardView) findViewById(R.id.historical3);
        historical4 = (CardView) findViewById(R.id.historical4);
        historical5 = (CardView) findViewById(R.id.historical5);
        historical1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), greatWallofChinaHeritage.class);
                startActivity(intent);
            }
        });
        historical2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tajMahalHeritage.class);
                startActivity(intent);
            }
        });
        historical3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pyramidOfGaizaHeritage.class);
                startActivity(intent);
            }
        });
        historical4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), hogiSophiaHeritage.class);
                startActivity(intent);
            }
        });
        historical5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ankwotWatHeritage.class);
                startActivity(intent);
            }
        });
    }
}