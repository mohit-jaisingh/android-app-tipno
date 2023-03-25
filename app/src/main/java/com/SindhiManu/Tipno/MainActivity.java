package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    TextInputLayout textInputLayout;

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
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ArrayList<String> items = new ArrayList<>();
        items.add("2022");
        items.add("2023");

        autoCompleteTxt = findViewById(R.id.autoCompleteTextView);
        textInputLayout = findViewById(R.id.year_dropdown);

        adapterItems = new ArrayAdapter<>(this,R.layout.dropdown_item, items);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(adapterItems);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setText(adapterItems.getItem(1),false);
        DisplayMonth.setCurYear(Integer.parseInt(adapterItems.getItem(1)));
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                DisplayMonth.setCurYear(Integer.parseInt(item));
                toast("Year Selected: " +item);
            }
        });

        Button janButton = findViewById(R.id.Jan);

        janButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(1);
            }
        });

        Button febButton = findViewById(R.id.Feb);
        febButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(2);
            }
        });

        Button marButton = findViewById(R.id.Mar);
        marButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(3);
            }
        });

        Button aprButton = findViewById(R.id.Apr);
        aprButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(4);
            }
        });

        Button mayButton = findViewById(R.id.May);
        mayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(5);
            }
        });

        Button junButton = findViewById(R.id.Jun);
        junButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(6);
            }
        });

        Button julButton = findViewById(R.id.Jul);
        julButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(7);
            }
        });

        Button augButton = findViewById(R.id.Aug);
        augButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(8);
            }
        });

        Button sepButton = findViewById(R.id.Sep);
        sepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(9);
            }
        });

        Button octButton = findViewById(R.id.Oct);
        octButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(10);
            }
        });

        Button novButton = findViewById(R.id.Nov);
        novButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
                DisplayMonth.setCurMonth(11);
            }
        });

        Button decButton = findViewById(R.id.Dec);
        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayMonth();
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

    public void openDisplayMonth(){

        Intent intent = new Intent(this, DisplayMonth.class);
        startActivity(intent);

    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

    public void openDisplayAarti(){
        Intent intent = new Intent(this, DisplayAarti.class);
        startActivity(intent);
    }

}