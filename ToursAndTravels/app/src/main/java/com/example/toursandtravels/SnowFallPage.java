package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SnowFallPage extends AppCompatActivity {
    CardView snow1,snow2,snow3,snow4,snow5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow_fall_page);
        snow1 = (CardView) findViewById(R.id.snow1);
        snow2 = (CardView) findViewById(R.id.snow2);
        snow3 = (CardView) findViewById(R.id.snow3);
        snow4 = (CardView) findViewById(R.id.snow4);
        snow5 = (CardView) findViewById(R.id.snow5);
        snow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NanitalPage.class);
                startActivity(intent);
            }
        });
        snow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GulmargPage.class);
                startActivity(intent);
            }
        });
        snow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShirakawaPage.class);
                startActivity(intent);
            }
        });
        snow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BanffPage.class);
                startActivity(intent);
            }
        });
        snow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RockyMountain.class);
                startActivity(intent);
            }
        });
    }
}