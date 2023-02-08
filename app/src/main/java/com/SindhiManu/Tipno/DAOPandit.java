package com.SindhiManu.Tipno;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPandit {

    private DatabaseReference databaseReference;

    public DAOPandit(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://sindhi-sangat-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference =  db.getReference(Pandit.class.getSimpleName());
    }

    public Task<Void> addPanditToDatabase(Pandit pandit){

        return databaseReference.push().setValue(pandit);
    }
}
