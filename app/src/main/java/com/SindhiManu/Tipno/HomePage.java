package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HomePage extends AppCompatActivity {

    @Override // Hardcoding the view so that phone's display settings do not interfere with UI
    protected void attachBaseContext(Context newBase) {

        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);

        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_home_page);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        AdView mAdView = findViewById(R.id.adView_home);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageButton tipnoButton = findViewById(R.id.tipno);

        tipnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTipno();
            }
        });

        ImageButton eventsButton = findViewById(R.id.events);

        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });

//        ImageButton BMM_Pandits = findViewById(R.id.contacts);
//
//        BMM_Pandits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fetchPanditsAndDisplay();
//            }
//        });

        ImageButton aarti = findViewById(R.id.aartis);
        aarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAartis();
            }
        });
    }

    public void openTipno(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void openEvents(){

        Intent intent = new Intent(this,Events.class);
        startActivity(intent);
    }

//    public void fetchPanditsAndDisplay(){
//        Intent intent = new Intent(this, DisplayPandits.class);
//        startActivity(intent);
//    }

    public void displayAartis(){
        Intent intent = new Intent(this, DisplayAartiMenu.class);
        startActivity(intent);
    }
}
