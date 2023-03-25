package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jsibbold.zoomage.ZoomageView;

public class DisplayMonth extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private ImageButton langOption;
    private static int curMonth = 1;
    boolean isGridCal = false; //Set default option
    boolean isLangHindi = false; // default option
    private static int curIndexOfList = 0;
    private static int curYear = 2023;

    private ZoomageView month, list;

    static int[] gridCal;
    static int[] listCalEng;
    static int[] listCalHin;

    static int[] gridCal_2023 = {R.drawable.jan_cal_2023, R.drawable.feb_cal_2023, R.drawable.mar_cal_2023,
            R.drawable.apr_cal_2023, R.drawable.may_cal_2023, R.drawable.jun_cal_2023, R.drawable.jul_cal_2023,
            R.drawable.aug_cal_2023, R.drawable.sep_cal_2023, R.drawable.oct_cal_2023, R.drawable.nov_cal_2023,
            R.drawable.dec_cal_2023};

    static int[] listCalEng_2023 = {
            R.drawable.jan_2023_1, R.drawable.jan_2023_2, R.drawable.feb_2023_1, R.drawable.feb_2023_2,
            R.drawable.mar_2023_1, R.drawable.mar_2023_2, R.drawable.apr_2023_1,R.drawable.apr_2023_2,
            R.drawable.may_2023_1,R.drawable.may_2023_2, R.drawable.jun_2023_1,R.drawable.jun_2023_2,
            R.drawable.jul_2023_1,R.drawable.jul_2023_2,R.drawable.aug_2023_1, R.drawable.aug_2023_2,
            R.drawable.aug_2023_3,R.drawable.sep_2023_1,R.drawable.sep_2023_2,R.drawable.oct_2023_1,
            R.drawable.oct_2023_2,R.drawable.nov_2023_1,R.drawable.nov_2023_2,R.drawable.dec_2023_1,
            R.drawable.dec_2023_2,R.drawable.jan_2024_1};

    static int[] listCalHin_2023 = {
            R.drawable.jan_2023_h1, R.drawable.jan_2023_h2,  R.drawable.feb_2023_h1,  R.drawable.feb_2023_h2,
            R.drawable.mar_2023_h1,  R.drawable.mar_2023_h2, R.drawable.apr_2023_h1,R.drawable.apr_2023_h2,
            R.drawable.may_2023_h1, R.drawable.may_2023_h2,R.drawable.jun_2023_h1, R.drawable.jun_2023_h2,
            R.drawable.jul_2023_h1,R.drawable.jul_2023_h2, R.drawable.aug_2023_h1,R.drawable.aug_2023_h2,
            R.drawable.aug_2023_h3, R.drawable.sep_2023_h1, R.drawable.sep_2023_h2,R.drawable.oct_2023_h1,
            R.drawable.oct_2023_h2, R.drawable.nov_2023_h1, R.drawable.nov_2023_h2,R.drawable.dec_2023_h1,
            R.drawable.dec_2023_h2,R.drawable.jan_2024_h1};

    static int[] gridCal_2022 = {R.drawable.jan_cal, R.drawable.feb_cal, R.drawable.mar_cal, R.drawable.apr_cal,
                  R.drawable.may_cal, R.drawable.jun_cal, R.drawable.jul_cal, R.drawable.aug_cal,
                  R.drawable.sep_cal, R.drawable.oct_cal, R.drawable.nov_cal, R.drawable.dec_cal};

    static int[] listCalEng_2022 =
            {R.drawable.download, R.drawable.download,R.drawable.download,R.drawable.download,
            R.drawable.download,R.drawable.download,R.drawable.apr_2022_1, R.drawable.apr_2022_2,
            R.drawable.may_2022_1, R.drawable.may_2022_2, R.drawable.jun_2022_1, R.drawable.jun_2022_2,
            R.drawable.jul_2022_1, R.drawable.jul_2022_2, R.drawable.aug_2022_1, R.drawable.aug_2022_2,
            R.drawable.sep_2022_1, R.drawable.sep_2022_2, R.drawable.oct_2022_1, R.drawable.oct_2022_2,
            R.drawable.nov_2022_1, R.drawable.nov_2022_2, R.drawable.dec_2022_1, R.drawable.dec_2022_2};

    static int[] listCalHin_2022 =
            {R.drawable.download, R.drawable.download,R.drawable.download,R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.apr_2022_h1, R.drawable.apr_2022_h2,
            R.drawable.may_2022_h1, R.drawable.may_2022_h2, R.drawable.jun_2022_h1, R.drawable.jun_2022_h2,
            R.drawable.jul_2022_h1, R.drawable.jul_2022_h2, R.drawable.aug_2022_h1, R.drawable.aug_2022_h2,
            R.drawable.sep_2022_h1, R.drawable.sep_2022_h2, R.drawable.oct_2022_h1, R.drawable.oct_2022_h2,
            R.drawable.nov_2022_h1, R.drawable.nov_2022_h2, R.drawable.dec_2022_h1, R.drawable.dec_2022_h2};

    public static void setCurMonth(int month){
        curMonth = month;
        curIndexOfList=(curMonth-1)*2;
    }
    public static void setCurYear(int year) {curYear = year; setCalendarResources();}

    public void moveToNextYear() {
        if(curYear==2022) {
            setCurMonth(1);
            setCurYear(2023);
        }
        else {
            Toast.makeText(this, "That's All!", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToPrevYear() {
        if (curYear == 2023) {
            setCurMonth(12);
            setCurYear(2022);
        }else{
            Toast.makeText(this, "That's All!", Toast.LENGTH_SHORT).show();
        }
    }

//    public static int getCurYear(){
//        return curYear;
//    }
//    public static int getCurMonth(){
//        return curMonth;
//    }

    private static void setCalendarResources(){
        switch (curYear){
            case 2022:
                gridCal = gridCal_2022;
                listCalEng = listCalEng_2022;
                listCalHin = listCalHin_2022;
                break;

            case 2023:
                gridCal = gridCal_2023;
                listCalEng = listCalEng_2023;
                listCalHin = listCalHin_2023;
                break;

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);

        super.attachBaseContext(newBase);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        //disabling screen capture
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.content_display_month);
        ImageButton calOption = findViewById(R.id.calendarOption);
        viewFlipper = findViewById(R.id.viewFlipper);
        Button prev = findViewById(R.id.prev);
        Button next = findViewById(R.id.next);
        month = findViewById(R.id.viewMonth);
        list = findViewById(R.id.viewList);
        langOption = findViewById(R.id.languageOption);

        if(isGridCal){
            langOption.setVisibility(View.INVISIBLE);
        }else langOption.setVisibility(View.VISIBLE);

        try{
            if(isGridCal){ //When Activity is called, display corresponding Month in chosen view.
                Glide.with(DisplayMonth.this).load(gridCal[curMonth-1]).into(month);
            }else  {

                if(curYear==2023 && curMonth>=9){
                    Glide.with(DisplayMonth.this).load(listCalEng[(curIndexOfList=(curMonth-1)*2)+1]).into(list);
                }else{
                    Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList=(curMonth-1)*2]).into(list);
                }

                viewFlipper.showNext();

                if(curYear == 2023 && listCalEng[curIndexOfList]== R.drawable.download) {
                    Toast.makeText(DisplayMonth.this, "Data for month " +curMonth +" will be available from end of March 2023",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(this,"Data Retrieve error", Toast.LENGTH_SHORT).show();
        }

        AdView mAdView = findViewById(R.id.adView_month);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGridCal){
                    if(curMonth>1) curMonth--;
                    else moveToPrevYear();
                    Glide.with(DisplayMonth.this).load(gridCal[curMonth-1]).into(month);
                }else  {
                    if(curIndexOfList>1){
                        Glide.with(DisplayMonth.this).load(listCalEng[--curIndexOfList]).into(list);
                        curMonth = (curIndexOfList+1) / 2;
                    }else{
                        moveToPrevYear();
                        Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList]).into(list);
                    }
                    if(curYear == 2023 && listCalEng[curIndexOfList+1]== R.drawable.download) {
                        Toast.makeText(DisplayMonth.this, "Data for month " +curMonth +" will be available from end of March 2023",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGridCal){
                    if(curMonth< gridCal_2022.length) curMonth++;
                    else moveToNextYear();
                    Glide.with(DisplayMonth.this).load(gridCal[curMonth-1]).into(month);
                }else {
                    if(curIndexOfList< listCalEng.length-1){
                        if(isLangHindi){
                            Glide.with(DisplayMonth.this).load(listCalHin[++curIndexOfList]).into(list);
                        }else{
                            Glide.with(DisplayMonth.this).load(listCalEng[++curIndexOfList]).into(list);
                        }
                        if(curYear == 2023 && listCalEng[curIndexOfList]== R.drawable.download) {
                            Toast.makeText(DisplayMonth.this, " Requested Data for month " +curMonth +" will be available from end of March 2023",
                                    Toast.LENGTH_LONG).show();
                        }
                        curMonth = (curIndexOfList+1) / 2;
                    }else{
                        moveToNextYear();
                        if(isLangHindi){
                            Glide.with(DisplayMonth.this).load(listCalHin[curIndexOfList]).into(list);
                        }else{
                            Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList]).into(list);
                        }
                    }
                }
            }

        });

        calOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGridCal){
                    isGridCal = false;
                    langOption.setVisibility(View.VISIBLE);
                    if(isLangHindi){
                        Glide.with(DisplayMonth.this).load(listCalHin[curIndexOfList=(curMonth-1)*2]).into(list);
                    }else{
                        Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList=(curMonth-1)*2]).into(list);
                    }
                    viewFlipper.showNext();
                }
                else {
                    isGridCal = true;
                    langOption.setVisibility(View.INVISIBLE);
                    curMonth = Math.min(((curIndexOfList / 2) + 1), 12);
                    Glide.with(DisplayMonth.this).load(gridCal[curMonth-1]).into(month);
                    viewFlipper.showNext();
                }


            }

        });

        langOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLangHindi){
                    isLangHindi = false;
                    Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList]).into(list);
                    langOption.setBackgroundResource(R.drawable.hindi_button_icon);
                }else{
                    isLangHindi = true;
                    Glide.with(DisplayMonth.this).load(listCalHin[curIndexOfList]).into(list);
                    langOption.setBackgroundResource(R.drawable.eng_lang_icon);

                }
            }
        });


    }

}