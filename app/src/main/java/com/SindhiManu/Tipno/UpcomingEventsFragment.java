package com.SindhiManu.Tipno;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpcomingEventsFragment extends Fragment {

    private ArrayList<Event> upcomingEventsList;
    private EventAdapter eventAdapter;
    private TextView noUpcomingEventsMessage;
    private RecyclerView upcomingEventRecyclerView;

    public UpcomingEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_events, container, false);

        upcomingEventRecyclerView = view.findViewById(R.id.upcoming_event_recycler_view);
        noUpcomingEventsMessage = view.findViewById(R.id.no_upcoming_events_message);
        upcomingEventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        upcomingEventsList = new ArrayList<>();
        eventAdapter = new EventAdapter(getContext(), upcomingEventsList, new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                // Handle item click for upcoming events
            }
        });

        upcomingEventRecyclerView.setAdapter(eventAdapter);

        // Load upcoming events data into upcomingEventsList if needed
        checkAndShowMessage();
        return view;
    }

    private void checkAndShowMessage() {
        if (upcomingEventsList.isEmpty()) {
            upcomingEventRecyclerView.setVisibility(View.GONE);
            noUpcomingEventsMessage.setVisibility(View.VISIBLE);
        } else {
            upcomingEventRecyclerView.setVisibility(View.VISIBLE);
            noUpcomingEventsMessage.setVisibility(View.GONE);
        }
    }
}
