package com.SindhiManu.Tipno;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DAOEvent {

    private DatabaseReference databaseReference;

    public DAOEvent(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://sindhi-sangat-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference =  db.getReference(Event.class.getSimpleName());
    }

    public Task<Void> addEventToDatabase(String eventKey, Event event){
        return databaseReference.child(eventKey).setValue(event);
    }
    /*
    public Task<Void> addeventToDatabase(event event){

        return databaseReference.push().setValue(event);
    }
    */
    public boolean getEvent(String eventKey) {
        final boolean[] exists = {false};
        DatabaseReference reference = databaseReference.child(eventKey);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exists[0] = dataSnapshot.exists();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("DAO Event: Check for existing value cancelled");
            }
        });
        return exists[0];
    }
    public Query get(){
        return databaseReference.orderByKey();
    }

    public Task<Void> updateEventInDatabase(String eventKey, Event updatedEvent) {
        return databaseReference.child(eventKey).setValue(updatedEvent);
    }

    public Task<Void> deleteEventFromDatabase(String eventKey) {
        return databaseReference.child(eventKey).removeValue();
    }
}
