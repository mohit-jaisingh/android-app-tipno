package com.SindhiManu.Tipno;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jsibbold.zoomage.ZoomageView;

public class DisplayFestivals extends AppCompatActivity {

    private static int curFest = 0;

    static int[] festivals = { R.drawable.fest_2023_1,R.drawable.fest_2023_2};

    public static void setCurAarti(int pageNo){
        curFest = pageNo;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.content_display_festivals);

        AdView mAdView = findViewById(R.id.adView_fest);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper_fest);

        ZoomageView festView = findViewById(R.id.viewFestivals);
        ZoomageView list = findViewById(R.id.viewList_fest);

        Button prev = findViewById(R.id.prev_fest);

        try{
            Glide.with(DisplayFestivals.this).load(festivals[curFest]).into(festView);
        }catch (Exception e){
            Toast.makeText(this,"Data Retrieve error", Toast.LENGTH_SHORT).show();
        }

//        AdView mAdView = findViewById(R.id.adView_aartis);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(curFest > 0){
                        Glide.with(DisplayFestivals.this).load(festivals[--curFest]).into(festView);
                    }
                }catch(Exception e){
                    Toast.makeText(DisplayFestivals.this,"Data Retrieve error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button next = findViewById(R.id.next_fest);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curFest < festivals.length-1){
                    Glide.with(DisplayFestivals.this).load(festivals[++curFest]).into(festView);
                }
            }
        });
    }

}