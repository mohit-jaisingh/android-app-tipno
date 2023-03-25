package com.SindhiManu.Tipno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jsibbold.zoomage.ZoomageView;

public class DisplayAarti extends AppCompatActivity {

    private static int curAarti = 0;

    static int[] aartis = { R.drawable.shree_jhulelal_ji_ki_aarti};

    public static void setCurAarti(int aartiNo){
        curAarti = aartiNo;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_aarti);

        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);

        ZoomageView aarti = findViewById(R.id.viewAarti);
        ZoomageView list = findViewById(R.id.viewListAarti);

        try{
            Glide.with(DisplayAarti.this).load(aartis[curAarti]).into(aarti);
        }catch (Exception e){
            Toast.makeText(this,"Data Retrieve error", Toast.LENGTH_SHORT).show();
        }

        AdView mAdView = findViewById(R.id.adView_aartis);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}