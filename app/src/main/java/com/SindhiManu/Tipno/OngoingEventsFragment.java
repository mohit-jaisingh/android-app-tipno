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

public class OngoingEventsFragment extends Fragment {


    private ArrayList<Event> ongoingEventsList;
    RecyclerView ongoingEventRecyclerView;
    private TextView noOngoingEventsMessage;
    private EventAdapter eventAdapter;

    public OngoingEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoing_events, container, false);

        // Initialize and set up your RecyclerView and Adapter
        ongoingEventRecyclerView = view.findViewById(R.id.ongoing_event_recycler_view);
        noOngoingEventsMessage = view.findViewById(R.id.no_ongoing_events_message);
        ongoingEventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ongoingEventsList = new ArrayList<>();
        eventAdapter = new EventAdapter(getContext(), ongoingEventsList, new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                // Handle item click for ongoing events
            }
        });

        ongoingEventRecyclerView.setAdapter(eventAdapter);

        // Load ongoing events data into ongoingEventsList if needed
//        checkAndShowMessage();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Load data when the tab is selected
        if (isVisibleToUser && ongoingEventsList.isEmpty()) {
            loadOngoingEventData();
        }
    }

    private void loadOngoingEventData() {
        // Implement the logic to load ongoing events data from the database
        // Update ongoingEventsList and notify the adapter
    }

//    private void checkAndShowMessage() {
//        if (ongoingEventsList.isEmpty()) {
//            ongoingEventRecyclerView.setVisibility(View.GONE);
//            noOngoingEventsMessage.setVisibility(View.VISIBLE);
//        } else {
//            ongoingEventRecyclerView.setVisibility(View.VISIBLE);
//            noOngoingEventsMessage.setVisibility(View.GONE);
//        }
//    }
}
