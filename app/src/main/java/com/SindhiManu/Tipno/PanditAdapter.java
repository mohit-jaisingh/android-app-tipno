package com.SindhiManu.Tipno;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PanditAdapter extends RecyclerView.Adapter<PanditAdapter.MyViewHolder> {

    Context context;
    List<Pandit> pandits;

    public PanditAdapter(Context context, ArrayList<Pandit> pandits) {
        this.context = context;
        this.pandits = pandits;
    }

    public void setItems(List<Pandit> pandits){
        this.pandits = pandits;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView panditName, address, city, state, pin;
        ListView phoneNos, emails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            panditName = itemView.findViewById(R.id.pandit_name);
            address = itemView.findViewById(R.id.pandit_address);
            city = itemView.findViewById(R.id.pandit_city);
            state = itemView.findViewById(R.id.pandit_state);
            pin = itemView.findViewById(R.id.pandit_pincode);
            phoneNos = itemView.findViewById(R.id.pandit_phone_no_list);
            emails = itemView.findViewById(R.id.pandit_email_list);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.each_pandit,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pandit pandit = pandits.get(position);
        holder.panditName.setText(pandit.getName());
        holder.address.setText(pandit.getAddress());
        holder.city.setText(pandit.getCity());
        holder.state.setText(pandit.getState());
        holder.pin.setText(pandit.getPin());

        List<String> phoneNos = pandit.getPhone_nos();
        List<String> emails = pandit.getEmail_ids();

        Log.d("PanditAdapter", "phoneNos: " + phoneNos.toString());
        Log.d("PanditAdapter", "emails: " + emails.toString());
        Log.d("PanditAdapter", "onBindViewHolder called for position: " + position);

        PhoneNoAdapter phoneNoAdapter = new PhoneNoAdapter(context,phoneNos);
        holder.phoneNos.setAdapter(phoneNoAdapter);


        EmailAdapter emailAdapter = new EmailAdapter(context,emails);
        holder.emails.setAdapter(emailAdapter);
    }

    @Override
    public int getItemCount() {
        return pandits.size();
    }
}
