package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BeachPage extends AppCompatActivity {
    CardView beach1,beach2,beach3,beach4,beach5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_page);
        beach1 = (CardView) findViewById(R.id.beach1);
        beach2 = (CardView) findViewById(R.id.beach2);
        beach3 = (CardView) findViewById(R.id.beach3);
        beach4 = (CardView) findViewById(R.id.beach4);
        beach5 = (CardView) findViewById(R.id.beach5);
        beach1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),playaDelAmorBeach.class);
                startActivity(intent);
            }
        });
        beach2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ipanemaBeach.class);
                startActivity(intent);
            }
        });
        beach3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), whiteHeavenBeach.class);
                startActivity(intent);
            }
        });
        beach4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), navagioBeach.class);
                startActivity(intent);
            }
        });
        beach5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), cocoaBeach.class);
                startActivity(intent);
            }
        });
    }
}