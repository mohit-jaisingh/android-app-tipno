package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import soup.neumorphism.NeumorphFloatingActionButton;

public class DisplayPandits extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NeumorphFloatingActionButton add_pandit;

    @Override // Hardcoding the view so that phone's display settings do not interfere with UI
    protected void attachBaseContext(Context newBase) {
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);

        super.attachBaseContext(newBase);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pandits);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        recyclerView = findViewById(R.id.recyclerView);
        add_pandit = findViewById(R.id.add_new_pandit);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayPandits.this));

        add_pandit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewPandit();
            }
        });
    }

    public void openAddNewPandit(){
        Intent intent = new Intent(this, AddNewPandit.class);
        startActivity(intent);
    }
}
