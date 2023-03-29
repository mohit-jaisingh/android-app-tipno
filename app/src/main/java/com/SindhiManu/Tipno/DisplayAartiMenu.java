package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_display_aarti_menu);

        NeumorphButton jhulelalji_aarti_btn = findViewById(R.id.jhulelal_aarti_button);

        jhulelalji_aarti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(0);
                displayAartis();
            }
        });

        NeumorphButton ganapati_ki_seva_btn = findViewById(R.id.ganesh_aarti1_button);
        ganapati_ki_seva_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(1);
                displayAartis();
            }
        });

        NeumorphButton jai_ganesh_aarti_btn = findViewById(R.id.ganesh_aarti2_button);
        jai_ganesh_aarti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(2);
                displayAartis();
            }
        });

        NeumorphButton sukh_karta_btn = findViewById(R.id.ganesh_aarti3_button);
        sukh_karta_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(3);
                displayAartis();
            }
        });

        NeumorphButton jai_shiv_onkara_btn = findViewById(R.id.shiva_aarti_button);
        jai_shiv_onkara_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(4);
                displayAartis();
            }
        });

        NeumorphButton jai_ambe_gauri_btn = findViewById(R.id.ambeji_aarti_button);
        jai_ambe_gauri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(5);
                displayAartis();
            }
        });

        NeumorphButton jai_jagadish_hare_btn = findViewById(R.id.jagdishji_aarti_button);
        jai_jagadish_hare_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(6);
                displayAartis();
            }
        });

        NeumorphButton satyanarayanji_aarti_btn = findViewById(R.id.satyanarayanji_aarti_button);
        satyanarayanji_aarti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(7);
                displayAartis();
            }
        });

        NeumorphButton jai_lakshmi_mata_btn = findViewById(R.id.lakshmiji_aarti_button);
        jai_lakshmi_mata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(8);
                displayAartis();
            }
        });

        NeumorphButton kali_mata_ki_aarti_btn = findViewById(R.id.kalimata_aarti_button);
        kali_mata_ki_aarti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(9);
                displayAartis();
            }
        });

        NeumorphButton hanuman_ji_ki_aarti = findViewById(R.id.hanumanji_aarti_button);
        hanuman_ji_ki_aarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayAarti.setCurAarti(10);
                displayAartis();
            }
        });

        AdView mAdView = findViewById(R.id.adView_aarti_menu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void displayAartis(){
        Intent intent = new Intent(this, DisplayAarti.class);
        startActivity(intent);
    }


}
