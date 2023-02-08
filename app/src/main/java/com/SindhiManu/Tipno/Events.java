package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Events extends AppCompatActivity {

    @Override // Hardcoding the view so that phone's display settings do not interfere with UI
    protected void attachBaseContext(Context newBase) {
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);

        super.attachBaseContext(newBase);
    }

//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    CollectionReference eventsRef = db.collection("events");


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }


}
