package com.SindhiManu.Tipno;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intent_main = new Intent(context, HomePage.class);
            intent_main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent_main);
        }
    }
}
