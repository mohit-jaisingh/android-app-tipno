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

    static int[] aartis = { R.drawable.shree_jhulelal_ji_ki_aarti_00,R.drawable.ganpati_ki_seva_01,
    R.drawable.jai_ganesh_aarti_02,R.drawable.sukh_karta_dukh_harta_03,R.drawable.jai_shiv_onkara_04,
            R.drawable.ambe_ji_ki_aarti_05, R.drawable.jagsish_ji_ki_aarti_06,
            R.drawable.satyanarayan_ji_ki_aarti_07,R.drawable.shri_lakshmi_ji_aarti_08,
            R.drawable.kali_mata_ki_aarti_09,R.drawable.hanuman_ji_ki_aarti_10};

    public static void setCurAarti(int aartiNo){
        curAarti = aartiNo;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_display_aarti);

        ZoomageView aarti = findViewById(R.id.viewAarti);

        try{
            Glide.with(DisplayAarti.this).load(aartis[curAarti]).into(aarti);
        }catch (Exception e){
            Toast.makeText(this,"Data Retrieve error", Toast.LENGTH_SHORT).show();
        }

//        AdView mAdView = findViewById(R.id.adView_aartis);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

}