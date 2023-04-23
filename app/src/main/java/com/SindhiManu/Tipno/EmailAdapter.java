package com.SindhiManu.Tipno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EmailAdapter extends ArrayAdapter<String> {
    private Context context;

    public EmailAdapter(Context context, List<String> emails) {
        super(context, 0, emails);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String phoneNo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.each_email, parent, false);
        }

        TextView phoneNoTextView = convertView.findViewById(R.id.email);
        phoneNoTextView.setText(phoneNo);

        return convertView;
    }
}