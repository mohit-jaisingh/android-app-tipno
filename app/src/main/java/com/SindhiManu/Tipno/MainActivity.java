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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter<String> adapterItems;
    private TextInputLayout textInputLayout;
    private int curYear;


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
        setContentView(R.layout.activity_main);

        Button janButton = findViewById(R.id.Jan);
        Button febButton = findViewById(R.id.Feb);
        Button marButton = findViewById(R.id.Mar);
        Button aprButton = findViewById(R.id.Apr);
        Button mayButton = findViewById(R.id.May);
        Button junButton = findViewById(R.id.Jun);
        Button julButton = findViewById(R.id.Jul);
        Button augButton = findViewById(R.id.Aug);
        Button sepButton = findViewById(R.id.Sep);
        Button octButton = findViewById(R.id.Oct);
        Button novButton = findViewById(R.id.Nov);
        Button decButton = findViewById(R.id.Dec);

        ArrayList<String> items = new ArrayList<>();
//        items.add("2022");
        items.add("2023");
        items.add("2024");

        autoCompleteTxt = findViewById(R.id.autoCompleteTextView);
        textInputLayout = findViewById(R.id.year_dropdown);

        adapterItems = new ArrayAdapter<>(this, R.layout.dropdown_item, items);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(adapterItems);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setText(adapterItems.getItem(1), false);
        DisplayMonth.setCurYear(Integer.parseInt(adapterItems.getItem(1)));
        curYear = Integer.parseInt(adapterItems.getItem(1));
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                DisplayMonth.setCurYear(Integer.parseInt(item));
                toast("Year Selected: " + item);
                curYear = Integer.parseInt(item);
            }
        });

        janButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(1);
                DisplayMonth.setCurMonth(1);
            }
        });


        febButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(2);
                DisplayMonth.setCurMonth(2);
            }
        });

        marButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(3);
                DisplayMonth.setCurMonth(3);
            }
        });

        aprButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(4);
                DisplayMonth.setCurMonth(4);
            }
        });

        mayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(5);
                DisplayMonth.setCurMonth(5);
            }
        });

        junButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(6);
                DisplayMonth.setCurMonth(6);
            }
        });

        julButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(7);
                DisplayMonth.setCurMonth(7);
            }
        });

        augButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(8);
                DisplayMonth.setCurMonth(8);
            }
        });

        sepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(9);
                DisplayMonth.setCurMonth(9);
            }
        });

        octButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(10);
                DisplayMonth.setCurMonth(10);
            }
        });

        novButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(11);
                DisplayMonth.setCurMonth(11);
            }
        });

        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth(12);
                DisplayMonth.setCurMonth(12);

            }
        });

        ImageButton jhulelal_ji = findViewById(R.id.jhulelal);
        jhulelal_ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayAarti();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }

    public void openDisplayMonth(int month) {
        if (curYear == 2024 && month > 4) {
            Toast.makeText(this, "Data will be available post Cheti Chand 2024", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, DisplayMonth.class);
        startActivity(intent);
    }

    public void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void openDisplayAarti() {
        Intent intent = new Intent(this, DisplayAarti.class);
        startActivity(intent);
    }


}