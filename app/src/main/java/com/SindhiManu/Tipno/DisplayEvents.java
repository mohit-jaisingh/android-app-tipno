package com.SindhiManu.Tipno;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.SindhiManu.Tipno.DAOEvent;
import com.SindhiManu.Tipno.Event;
import com.SindhiManu.Tipno.EventAdapter;
import com.SindhiManu.Tipno.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DisplayEvents extends AppCompatActivity {

    private DatabaseReference database;
    private EventAdapter eventAdapter;
    private ArrayList<Event> eventList;
    private RecyclerView recyclerView;
    private ArrayList<Event> upcomingEventList;
    private ArrayList<Event> ongoingEventList;
    private ArrayList<Event> pastEventList;
    private FirebaseUser user;

    @Override
    protected void attachBaseContext(Context newBase) {
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);

        user = FirebaseAuth.getInstance().getCurrentUser();


        upcomingEventList = new ArrayList<>();
        ongoingEventList = new ArrayList<>();
        pastEventList = new ArrayList<>();

        recyclerView = findViewById(R.id.event_recycler_view);

        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(this, eventList, new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                // Handle item click here, e.g., open a new activity or show details
                // of the selected event.
                // You can use 'event' object to get details of the clicked event.
                // Example:
                Toast.makeText(DisplayEvents.this, event.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(eventAdapter);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(1);
        eventAdapter.setItems(ongoingEventList);
        ViewTreeObserver viewTreeObserver = viewPager.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                // Remove the listener to avoid multiple calls
                viewPager.getViewTreeObserver().removeOnPreDrawListener(this);

                // Update the visibility of TextView after setting the current item
                updateTextViewVisibility(ongoingEventList.size(), R.id.no_ongoing_events_message);

                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Not needed for this implementation
            }

            @Override
            public void onPageSelected(int position) {
                // Load data when the tab is selected
                ArrayList<Event> dispList = new ArrayList<>();
                switch (position) {
                    case 0:
                        dispList = upcomingEventList;
                        updateTextViewVisibility(upcomingEventList.size(), R.id.no_upcoming_events_message);
                        break;
                    case 1:
                        dispList = ongoingEventList;
                        updateTextViewVisibility(ongoingEventList.size(), R.id.no_ongoing_events_message);
                        break;
                    case 2:
                        dispList = pastEventList;
                        updateTextViewVisibility(pastEventList.size(), R.id.no_past_events_message);
                        break;
                }
                eventAdapter.setItems(dispList);
                recyclerView.setLayoutManager(new LinearLayoutManager(DisplayEvents.this));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Not needed for this implementation
            }
        });

        database = FirebaseDatabase.getInstance("https://sindhi-sangat-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Events");
        loadEventData();

    }

    private void updateTextViewVisibility(int eventListSize, int textViewId) {
        // Find the TextView based on its ID
        TextView noEventsTextView = findViewById(textViewId);

        // Set visibility based on the size of the event list
        if (eventListSize == 0) {
            noEventsTextView.setVisibility(View.VISIBLE);
        } else {
            noEventsTextView.setVisibility(View.GONE);
        }
    }

    private void loadEventData() {
        DAOEvent daoEvent = new DAOEvent();
        daoEvent.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                upcomingEventList.clear();
                ongoingEventList.clear();
                pastEventList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Event event = data.getValue(Event.class);

                    // Check if the event is upcoming, ongoing, or past
                    if (event != null && event.getEventDate() != null && event.getEventStartTime() != null && event.getEventEndTime() != null) {
                        String currentDate = getCurrentDate();
                        String currentTime = getCurrentTime();

                        if (isEventUpcoming(event.getEventDate(), event.getEventStartTime(), currentDate, currentTime)) {
                            // Add to upcoming events
                            event.setEventStatus("upcoming");
                            upcomingEventList.add(event);
                        } else if (isEventOngoing(event.getEventDate(), event.getEventStartTime(), event.getEventEndTime(), currentDate, currentTime)) {
                            // Add to ongoing events
                            event.setEventStatus("ongoing");
                            ongoingEventList.add(event);
                        } else {
                            // Add to past events
                            event.setEventStatus("past");
                            pastEventList.add(event);
                        }
                    }
                }
                System.out.println("Upcoming Events: " + upcomingEventList.size());
                System.out.println("Ongoing Events: " + ongoingEventList.size());
                System.out.println("Past Events: " + pastEventList.size());


                // Update the RecyclerView
                updateRecyclerView();

//                recyclerView.setLayoutManager(new LinearLayoutManager(DisplayEvents.this));
                // Notify the adapter that the data has changed
//                eventAdapter.notifyDataSetChanged();
            }


            private void updateRecyclerView() {
                // Notify the adapter that the data has changed
                eventAdapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(DisplayEvents.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }

    private boolean isEventUpcoming(String eventDate, String eventStartTime, String currentDate, String currentTime) {
        // Compare the event date with the current date
        int dateComparison = currentDate.compareTo(eventDate);

        if (dateComparison < 0) {
            // The event date is in the future, so it is upcoming
            return true;
        } else if (dateComparison == 0) {
            // The event date is today, check the start time
            return currentTime.compareTo(eventStartTime) < 0;
        } else {
            // The event date has passed
            return false;
        }
    }

    private boolean isEventOngoing(String eventDate, String eventStartTime, String eventEndTime, String currentDate, String currentTime) {
        // Compare the event date with the current date
        int dateComparison = currentDate.compareTo(eventDate);

        if (dateComparison == 0) {
            // The event is today, check if the current time is within the start and end time
            return currentTime.compareTo(eventStartTime) >= 0 && currentTime.compareTo(eventEndTime) <= 0;
        } else {
            // The event date has passed
            return false;
        }
    }

    private String getCurrentDate() {
        // Use SimpleDateFormat to format the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    private String getCurrentTime() {
        // Use SimpleDateFormat to format the current time
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(new Date());
    }
}
