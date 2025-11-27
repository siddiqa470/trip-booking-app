package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HillPage extends AppCompatActivity {
    CardView hill1,hill2,hill3,hill4,hill5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_page);
        hill1 = (CardView) findViewById(R.id.hill1);
        hill2 = (CardView) findViewById(R.id.hill2);
        hill3 = (CardView) findViewById(R.id.hill3);
        hill4 = (CardView) findViewById(R.id.hill4);
        hill5 = (CardView) findViewById(R.id.hill5);
        hill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), grindelwaldHill.class);
                startActivity(intent);
            }
        });
        hill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), hallstattHill.class);
                startActivity(intent);
            }
        });
        hill3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), machupicchuuHill.class);
                startActivity(intent);
            }
        });
        hill4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tablemountainHill.class);
                startActivity(intent);
            }
        });
        hill5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), shimlaHill.class);
                startActivity(intent);
            }
        });
    }
}