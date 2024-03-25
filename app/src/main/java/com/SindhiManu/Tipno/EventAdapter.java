package com.SindhiManu.Tipno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    List<Event> events;

    // Define an interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    private OnItemClickListener onItemClickListener;

    public EventAdapter(Context context, List<Event> events, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.events = events;
        this.onItemClickListener = onItemClickListener;
    }

    public EventAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eventName, eventLocation, eventDate, eventDescription, eventStartTime, eventEndTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.event_name);
            eventLocation = itemView.findViewById(R.id.event_location);
            eventDate = itemView.findViewById(R.id.event_date);
            eventStartTime = itemView.findViewById(R.id.event_start_time);
            eventEndTime = itemView.findViewById(R.id.event_end_time);
            eventDescription = itemView.findViewById(R.id.event_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(events.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.each_event, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Event event = events.get(position);
        holder.eventName.setText(event.getName());
        holder.eventLocation.setText(event.getLocation());
        holder.eventDate.setText(event.getEventDate());
        holder.eventStartTime.setText(event.getEventStartTime());
        holder.eventEndTime.setText(event.getEventEndTime());
        holder.eventDescription.setText(event.getEventDescription());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setItems(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }
}
