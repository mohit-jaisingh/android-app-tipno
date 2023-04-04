package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

//        List<String> testDeviceIds = Collections.singletonList("DCAB86D21663C265D075B98F6C73A67C");
//        RequestConfiguration configuration = new RequestConfiguration.Builder()
//                .setTestDeviceIds(testDeviceIds)
//                .build();
//
//        MobileAds.setRequestConfiguration(configuration);

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

        ImageButton aarti = findViewById(R.id.aartis);
        aarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAartis();
            }
        });

        ImageButton festivals = findViewById(R.id.festivals);
        festivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFestivals();
            }
        });

//        ImageButton eventsButton = findViewById(R.id.events);
//
//        eventsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openEvents();
//            }
//        });

//        ImageButton BMM_Pandits = findViewById(R.id.contacts);
//
//        BMM_Pandits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fetchPanditsAndDisplay();
//            }
//        });

    }

    public void openTipno(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void displayFestivals(){
        Intent intent = new Intent(this, DisplayFestivals.class);
        startActivity(intent);
    }

//    public void openEvents(){
//
//        Intent intent = new Intent(this,Events.class);
//        startActivity(intent);
//    }

//    public void fetchPanditsAndDisplay(){
//        Intent intent = new Intent(this, DisplayPandits.class);
//        startActivity(intent);
//    }

    public void displayAartis(){
        Intent intent = new Intent(this, DisplayAartiMenu.class);
        startActivity(intent);
    }
}
