package com.SindhiManu.Tipno;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PastEventsFragment extends Fragment {

    private ArrayList<Event> pastEventsList;
    private EventAdapter eventAdapter;
    private TextView noPastEventsMessage;
    private RecyclerView pastEventRecyclerView;

    public PastEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_past_events, container, false);

        pastEventRecyclerView = view.findViewById(R.id.past_event_recycler_view);
        noPastEventsMessage = view.findViewById(R.id.no_past_events_message);
        pastEventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        pastEventsList = new ArrayList<>();
        eventAdapter = new EventAdapter(getContext(), pastEventsList, new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {

            }
        });

        pastEventRecyclerView.setAdapter(eventAdapter);
//        checkAndShowMessage();
        return view;
    }

//    public void checkAndShowMessage() {
//        System.out.println("PastEventsFragment pastEventsList size: " + pastEventsList.size());
//        if (pastEventsList.isEmpty()) {
//            pastEventRecyclerView.setVisibility(View.GONE);
//            noPastEventsMessage.setVisibility(View.VISIBLE);
//        } else {
//            pastEventRecyclerView.setVisibility(View.VISIBLE);
//            noPastEventsMessage.setVisibility(View.GONE);
//        }
//    }
}

