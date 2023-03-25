package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import soup.neumorphism.NeumorphButton;

public class DisplayAartiMenu extends AppCompatActivity {

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
        setContentView(R.layout.activity_display_aarti_menu);

        AdView mAdView = findViewById(R.id.adView_aarti_menu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        NeumorphButton jhulelalji_aarti_btn = findViewById(R.id.jhulelal_aarti_button);

        jhulelalji_aarti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(0);
                displayAartis();
            }
        });


    }

    public void displayAartis(){
        Intent intent = new Intent(this, DisplayAarti.class);
        startActivity(intent);
    }


}
