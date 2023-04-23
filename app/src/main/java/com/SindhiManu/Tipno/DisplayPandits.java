package com.SindhiManu.Tipno;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import soup.neumorphism.NeumorphFloatingActionButton;

public class DisplayPandits extends AppCompatActivity {

    DatabaseReference database;
    PanditAdapter myAdapter;
    ArrayList<Pandit> pandit_list;
    LinearLayout errorLayout;
    AutoCompleteTextView autoCompleteTxt;
    TextInputLayout textInputLayout;
    ArrayAdapter<String> adapterItems;

    DAOPandit dao = new DAOPandit();
    boolean isDataLoaded;

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
        RecyclerView pandit_recyclerView = findViewById(R.id.pandit_recycler_view);

        AdView mAdView = findViewById(R.id.adView_disp_pandit);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        database = FirebaseDatabase.getInstance("https://sindhi-sangat-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Pandit");

        pandit_recyclerView.setHasFixedSize(true);
        pandit_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        autoCompleteTxt = findViewById(R.id.autoCompleteTextView);
        textInputLayout = findViewById(R.id.city_dropdown);
        String[] states = getResources().getStringArray(R.array.array_indian_states);

        adapterItems = new ArrayAdapter<>(this,R.layout.dropdown_item, states);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedState = parent.getItemAtPosition(position).toString();

                pandit_recyclerView.setHasFixedSize(true);
                pandit_recyclerView.setLayoutManager(new LinearLayoutManager(DisplayPandits.this));
                pandit_list = new ArrayList<>();

                // Filter the Pandit data to only show data for the selected city
//                List<Pandit> filteredPanditList = filterPanditData(selectedCity);
                // Update the RecyclerView adapter with the filtered Pandit data
//                myAdapter.setItems(filteredPanditList);

                myAdapter = new PanditAdapter(DisplayPandits.this, pandit_list);
                pandit_recyclerView.setAdapter(myAdapter);
                dao = new DAOPandit();
                errorLayout = findViewById(R.id.error_layout);
                isDataLoaded = false;
                LoadData(selectedState);

                if(!isDataLoaded){
//            Toast.makeText(this, "Internet is not connected or there is some error in loading the data", Toast.LENGTH_LONG).show();
                    errorLayout.setVisibility(View.VISIBLE);
                }
            }
        });

//        pandit_list = new ArrayList<>();
//        myAdapter = new PanditAdapter(this, pandit_list);
//        pandit_recyclerView.setAdapter(myAdapter);
//        dao = new DAOPandit();
//        errorLayout = findViewById(R.id.error_layout);
//        isDataLoaded = false;

        Button add_data = findViewById(R.id.request_data_add);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formLink = "https://forms.gle/VxSiusxVyGrpkNR49";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(formLink));
                startActivity(intent);
            }
        });

    }

    private List<Pandit> filterPanditData(String selectedState, List<Pandit> pandits) {
        List<Pandit> filteredList = new ArrayList<>();
        System.out.println("Selected city : " + selectedState);
        for (Pandit pandit : pandits) {
            System.out.println(" city of pandit: " + pandit.getState());
            if (pandit.getState().equals(selectedState)) {
                filteredList.add(pandit);
            }
        }
        return filteredList;
    }


    private void LoadData(String state){
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<Pandit> pandits_ = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren()){
                    Pandit pan = data.getValue(Pandit.class);
                    pandits_.add(pan);
                }

                List<Pandit> filteredPanditList = filterPanditData(state, pandits_);
                if(filteredPanditList.size() == 0){
                    //TODO: show message to email the details in text view and set its visibility accordingly.
                }
                myAdapter.setItems(filteredPanditList);
                myAdapter.notifyDataSetChanged();

                isDataLoaded = true; // set the value to true when the data is loaded
                errorLayout.setVisibility(View.GONE); // hide the error layout when the data is loaded

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorLayout.setVisibility(View.GONE); // hide the error layout when the data is loaded
            }
        });
    }


//    private void printAllDetails_Pandit(Pandit pan){
//
//        System.out.println(pan.getName());
//        System.out.println(pan.getAddress());
//        System.out.println(pan.getState());
//        System.out.println(pan.getCity());
//        System.out.println(pan.getPin());
//
//        ArrayList<String> phoneNos = pan.getPhone_nos();
//        int no = 1;
//        for(String phoneNo: phoneNos){
//            System.out.println("Phone no " + no++ + " : " + phoneNo);
//        }
//        ArrayList<String> emails = pan.getEmail_ids();
//        int no_e = 1;
//        for(String email: emails){
//            System.out.println("Email " + no_e + " : " + email);
//        }
//
//    }
}
