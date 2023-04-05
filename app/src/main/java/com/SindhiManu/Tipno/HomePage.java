package com.SindhiManu.Tipno;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import soup.neumorphism.NeumorphButton;

public class HomePage extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

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

        scheduleNotification(getNotification("अड़हु टिपणो डिठो अथवा?", "Tap notification to view Tipno"));
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

        NeumorphButton share_button = findViewById(R.id.share_app_btn);
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp(HomePage.this);
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

    @Override
    protected void onRestart() {
        activateFeedbackDialog();
        super.onRestart();
    }

    ReviewInfo reviewInfo;
    ReviewManager manager;

    private void activateFeedbackDialog() {
        manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                reviewInfo = task.getResult();
            } else {
                // There was some problem, log or handle the error code.
                Toast.makeText(this, "Review failed to start", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void openTipno() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void displayFestivals() {
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

    public void displayAartis() {
        Intent intent = new Intent(this, DisplayAartiMenu.class);
        startActivity(intent);
    }

    public void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the Sindhi Sangat App: Sindhi Tipno and Aarti app \n" +
                "Download Now: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share With"));
    }

    private void scheduleNotification (Notification notification) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;

        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_IMMUTABLE ) ;

        long futureInMillis = SystemClock. elapsedRealtime () + 5000 ;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);

    }
    private Notification getNotification (String title, String content) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle(title) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.calendar_icon ) ;
        builder.setAutoCancel( true ) ;
        builder.setContentIntent(pendingIntent);
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }


}
