package com.SindhiManu.Tipno;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import soup.neumorphism.NeumorphFloatingActionButton;

public class DisplayPandits extends AppCompatActivity {

    DatabaseReference database;
    PanditAdapter myAdapter;
    ArrayList<Pandit> pandit_list;

    DAOPandit dao = new DAOPandit();

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
//        NeumorphFloatingActionButton add_pandit = findViewById(R.id.add_new_pandit);

        pandit_recyclerView.setHasFixedSize(true);
        pandit_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pandit_list = new ArrayList<>();
        myAdapter = new PanditAdapter(this, pandit_list);
        pandit_recyclerView.setAdapter(myAdapter);
        dao = new DAOPandit();
        LoadData();

//        add_pandit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openAddNewPandit();
//            }
//        });

//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                pandit_list.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    Pandit pandit = dataSnapshot.getValue(Pandit.class);
//                    pandit_list.add(pandit);
//                }
//
//                myAdapter.notifyItemInserted(pandit_list.size()-1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

    }

    private void LoadData(){
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Pandit> pandits_ = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    Pandit pan = data.getValue(Pandit.class);
                    pandits_.add(pan);
                }
                myAdapter.setItems(pandits_);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void openAddNewPandit(){
//        Intent intent = new Intent(this, AddNewPandit.class);
//        startActivity(intent);
//    }
}
