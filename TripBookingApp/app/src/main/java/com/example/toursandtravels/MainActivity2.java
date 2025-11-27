package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private static class Place {
        String name;
        Class<?> activityClass;

        Place(String name, Class<?> activityClass) {
            this.name = name;
            this.activityClass = activityClass;
        }
    }

    private List<Place> searchablePlaces;

    ShapeableImageView beach, snow, island, hill, historical;
    ImageView eiffelTower, colosseum, statueLibraty, forbiddenCity, mountfuji, profile;
    ImageView likeEiffel, likeColosseum, likeStatue, likeForbiddenCity, likeMountfuji;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initializeViews();
        setupClickListeners();
        initializeSearchablePlaces();
        setupSearch();
    }

    private void initializeViews() {
        beach = findViewById(R.id.beach);
        snow = findViewById(R.id.snowland);
        island = findViewById(R.id.island);
        hill = findViewById(R.id.hill);
        historical = findViewById(R.id.historical);
        eiffelTower = findViewById(R.id.eiffelTower);
        colosseum = findViewById(R.id.colosseum);
        statueLibraty = findViewById(R.id.statueLibraty);
        forbiddenCity = findViewById(R.id.forbiddenCity);
        mountfuji = findViewById(R.id.mountfuji);
        profile = findViewById(R.id.profile);
        searchEditText = findViewById(R.id.search_txt);

        // Heart Icons
        likeEiffel = findViewById(R.id.like_eiffel);
        likeColosseum = findViewById(R.id.like_colosseum);
        likeStatue = findViewById(R.id.like_statue);
        likeForbiddenCity = findViewById(R.id.like_forbidden_city);
        likeMountfuji = findViewById(R.id.like_mountfuji);
    }

    private void initializeSearchablePlaces() {
        searchablePlaces = new ArrayList<>();
        // Categories
        searchablePlaces.add(new Place("Beach", BeachPage.class));
        searchablePlaces.add(new Place("Snow", SnowFallPage.class));
        searchablePlaces.add(new Place("Island", IslandPage.class));
        searchablePlaces.add(new Place("Hill", HillPage.class));
        searchablePlaces.add(new Place("Historical", HistoricalPage.class));

        // Visited Places
        searchablePlaces.add(new Place("Eiffel Tower", eiffelTowerPage.class));
        searchablePlaces.add(new Place("The Colosseum", theColosseumPage.class));
        searchablePlaces.add(new Place("Statue of Liberty", statueofLibratyPage.class));
        searchablePlaces.add(new Place("Forbidden City", forbiddenCityPage.class));
        searchablePlaces.add(new Place("Mount Fuji", mountFujiPage.class));
        searchablePlaces.add(new Place("Taj Mahal", tajMahalHeritage.class));
        searchablePlaces.add(new Place("Grindelwald", grindelwaldHill.class));
        searchablePlaces.add(new Place("Cocoa Beach", cocoaBeach.class));
        searchablePlaces.add(new Place("Hagia Sophia", hogiSophiaHeritage.class));
        searchablePlaces.add(new Place("Shimla", shimlaHill.class));
        searchablePlaces.add(new Place("Hallstatt", hallstattHill.class));
        searchablePlaces.add(new Place("Phuket", PhuketPage.class));
        searchablePlaces.add(new Place("Navagio Beach", navagioBeach.class));
        searchablePlaces.add(new Place("Nainital", NanitalPage.class));
        searchablePlaces.add(new Place("Gulmarg", GulmargPage.class));
        searchablePlaces.add(new Place("Great Wall of China", greatWallofChinaHeritage.class));
        searchablePlaces.add(new Place("Bora Bora", BoraBoraPage.class));
        searchablePlaces.add(new Place("Ipanema Beach", ipanemaBeach.class));
        searchablePlaces.add(new Place("Bali", BaliPage.class));
        searchablePlaces.add(new Place("Langkawi", LangkawiPage.class));
        searchablePlaces.add(new Place("Banff", BanffPage.class));
        searchablePlaces.add(new Place("Playa Del Amor", playaDelAmorBeach.class));
        searchablePlaces.add(new Place("Angkor Wat", ankwotWatHeritage.class));
        searchablePlaces.add(new Place("Machu Picchu", machupicchuuHill.class));
        searchablePlaces.add(new Place("Whitehaven Beach", whiteHeavenBeach.class));
        searchablePlaces.add(new Place("Islas Secas", IslasSecasPage.class));
        searchablePlaces.add(new Place("Pyramid of Giza", pyramidOfGaizaHeritage.class));
    }

    private void setupSearch() {
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchQuery = v.getText().toString().trim();
                    performSearch(searchQuery);
                    return true;
                }
                return false;
            }
        });
    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            return;
        }

        for (Place place : searchablePlaces) {
            if (place.name.equalsIgnoreCase(query) || place.name.toLowerCase().contains(query.toLowerCase())) {
                Intent intent = new Intent(getApplicationContext(), place.activityClass);
                startActivity(intent);
                return; // Found a match, so exit
            }
        }

        // If no match was found
        Toast.makeText(this, "Place not found: " + query, Toast.LENGTH_SHORT).show();
    }

    private final View.OnClickListener likeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView heartIcon = (ImageView) v;
            // Use the tag to store the liked state
            if (heartIcon.getTag() != null && heartIcon.getTag().equals("liked")) {
                // If already liked, unlike it by setting the original drawable
                heartIcon.setBackgroundResource(R.drawable.likebutton);
                heartIcon.setTag("unliked");
            } else {
                // If not liked, like it by setting the new filled drawable
                heartIcon.setBackgroundResource(R.drawable.likebutton_filled);
                heartIcon.setTag("liked");
            }
        }
    };

    private void setupClickListeners() {
        beach.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BeachPage.class)));
        snow.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SnowFallPage.class)));
        island.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), IslandPage.class)));
        hill.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), HillPage.class)));
        historical.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), HistoricalPage.class)));
        eiffelTower.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), eiffelTowerPage.class)));
        colosseum.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), theColosseumPage.class)));
        statueLibraty.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), statueofLibratyPage.class)));
        forbiddenCity.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), forbiddenCityPage.class)));
        mountfuji.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), mountFujiPage.class)));
        profile.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AccountPage.class)));

        // Set click listeners for heart icons
        likeEiffel.setOnClickListener(likeClickListener);
        likeColosseum.setOnClickListener(likeClickListener);
        likeStatue.setOnClickListener(likeClickListener);
        likeForbiddenCity.setOnClickListener(likeClickListener);
        likeMountfuji.setOnClickListener(likeClickListener);
    }
}
