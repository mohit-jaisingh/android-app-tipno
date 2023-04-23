package com.SindhiManu.Tipno;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PhoneNoAdapter extends ArrayAdapter<String> {

    private static class ViewHolder {
        TextView phoneNoTextView;
    }

    public PhoneNoAdapter(Context context, List<String> phoneNos) {
        super(context, R.layout.each_phone_no, phoneNos);
        Log.d("PhoneNoAdapter", "size of phoneNos passed : " +phoneNos.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Log.d("PhoneNoAdapter", "getView called for position: " + position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.each_phone_no, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.phoneNoTextView = convertView.findViewById(R.id.phone_no);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String phoneNo = getItem(position);
        viewHolder.phoneNoTextView.setText(phoneNo);

        return convertView;
    }


}
