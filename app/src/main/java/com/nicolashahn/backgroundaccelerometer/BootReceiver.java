package com.nicolashahn.backgroundaccelerometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    static final String LOG_TAG = MyActivity.class.getSimpleName();
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceLauncher = new Intent(context, BackgroundAccelerometerService.class);
        context.startService(serviceLauncher);
        Log.e(LOG_TAG, "BootReceiver started service");
    }
}
