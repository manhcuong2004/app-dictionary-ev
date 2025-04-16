package com.example.app_dictionary_ev;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // Initialize CardViews
        CardView historyCard = findViewById(R.id.cardView_History);
        CardView favoriteCard = findViewById(R.id.cardView_Favourite);
        CardView docsCard = findViewById(R.id.cardView_Docs);
        CardView settingsCard = findViewById(R.id.cardView_Settings);

        // Set click listeners for each CardView
        historyCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, HistoryScreen.class);
            startActivity(intent);
        });

        favoriteCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, HistoryScreen.class);
            startActivity(intent);
        });

        docsCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, HistoryScreen.class);
            startActivity(intent);
        });

        settingsCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, HistoryScreen.class);
            startActivity(intent);
        });
    }
}