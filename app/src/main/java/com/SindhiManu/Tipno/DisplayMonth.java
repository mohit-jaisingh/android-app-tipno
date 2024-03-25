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

    static int[] listCalHin_2024 = {R.drawable.jan_2024_h1, R.drawable.jan_2024_h2, R.drawable.feb_2024_h1,
            R.drawable.feb_2024_h2, R.drawable.mar_2024_h1, R.drawable.mar_2024_h2, R.drawable.apr_2024_h1};

    static int[] listCalEng_2024 = {R.drawable.jan_2024_1, R.drawable.jan_2024_2, R.drawable.feb_2024_1,
            R.drawable.feb_2024_2, R.drawable.mar_2024_1, R.drawable.mar_2024_2, R.drawable.apr_2024_1};

    static int[] gridCal_2024 = {R.drawable.jan_cal_2024, R.drawable.feb_cal_2024, R.drawable.mar_cal_2024,
            R.drawable.apr_cal_2024, R.drawable.may_cal_2024, R.drawable.jun_cal_2024, R.drawable.jul_cal_2024,
            R.drawable.aug_cal_2024, R.drawable.sep_cal_2024, R.drawable.oct_cal_2024, R.drawable.nov_cal_2024,
            R.drawable.dec_cal_2024};

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
            R.drawable.dec_2023_2};

    static int[] listCalHin_2023 = {
            R.drawable.jan_2023_h1, R.drawable.jan_2023_h2,  R.drawable.feb_2023_h1,  R.drawable.feb_2023_h2,
            R.drawable.mar_2023_h1,  R.drawable.mar_2023_h2, R.drawable.apr_2023_h1,R.drawable.apr_2023_h2,
            R.drawable.may_2023_h1, R.drawable.may_2023_h2,R.drawable.jun_2023_h1, R.drawable.jun_2023_h2,
            R.drawable.jul_2023_h1,R.drawable.jul_2023_h2, R.drawable.aug_2023_h1,R.drawable.aug_2023_h2,
            R.drawable.aug_2023_h3, R.drawable.sep_2023_h1, R.drawable.sep_2023_h2,R.drawable.oct_2023_h1,
            R.drawable.oct_2023_h2, R.drawable.nov_2023_h1, R.drawable.nov_2023_h2,R.drawable.dec_2023_h1,
            R.drawable.dec_2023_h2};

    public static void setCurMonth(int month){
        curMonth = month;
        curIndexOfList=(curMonth-1)*2;
    }
    public static void setCurYear(int year) {
        curYear = year;
        setCalendarResources();
    }

    public void moveToNextYear() {

        if(curYear==2023) {
            setCurMonth(1);
            setCurYear(2024);
        }
        else{
            Toast.makeText(this, "That's All!", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToPrevYear() {

        if (curYear == 2024) {
            setCurMonth(12);
            setCurYear(2023);
        }
        else{
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
            case 2023:
                gridCal = gridCal_2023;
                listCalEng = listCalEng_2023;
                listCalHin = listCalHin_2023;
                break;

            case 2024:
                gridCal = gridCal_2024;
                listCalEng = listCalEng_2024;
                listCalHin = listCalHin_2024;
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

        try {
            if (isGridCal) {
                Glide.with(DisplayMonth.this).load(gridCal[curMonth - 1]).into(month);
            } else {
                if (curYear == 2023) {
                    curIndexOfList = (curMonth >= 9) ? (curMonth - 1) * 2 + 1 : (curMonth - 1) * 2;
                } else {
                    curIndexOfList = (curMonth - 1) * 2;
                }
                Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList]).into(list);
                viewFlipper.showNext();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Data will be available by Cheti Chand 2024", Toast.LENGTH_SHORT).show();
            Glide.with(DisplayMonth.this).load(R.drawable.download).into(list);
            viewFlipper.showNext();
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
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGridCal){
                    if(curMonth < 12) curMonth++;
                    else moveToNextYear();
                    Glide.with(DisplayMonth.this).load(gridCal[curMonth-1]).into(month);
                }else {
                    if(curIndexOfList< listCalEng.length-1){
                        if(isLangHindi){
                            Glide.with(DisplayMonth.this).load(listCalHin[++curIndexOfList]).into(list);
                        }else{
                            Glide.with(DisplayMonth.this).load(listCalEng[++curIndexOfList]).into(list);
                        }
                        curMonth = (curIndexOfList+1) / 2;
                    }else{
                        int curY = curYear;
                        moveToNextYear();
                        if(curY == curYear) return;
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
                    curIndexOfList = (curMonth-1)*2;
                    if(curMonth > 8) curIndexOfList++;

                    if((curMonth-1)*2 < listCalEng.length) {
                        if (isLangHindi) {
                            Glide.with(DisplayMonth.this).load(listCalHin[curIndexOfList]).into(list);
                        } else {
                            Glide.with(DisplayMonth.this).load(listCalEng[curIndexOfList]).into(list);
                        }
                    } else{
                        Toast.makeText(DisplayMonth.this, "Data will be available by Cheti Chand 2024", Toast.LENGTH_SHORT).show();
                    }
                    viewFlipper.showNext();
                } else {
                    isGridCal = true;
                    langOption.setVisibility(View.INVISIBLE);
                    if(curIndexOfList >= 16) curIndexOfList--;
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