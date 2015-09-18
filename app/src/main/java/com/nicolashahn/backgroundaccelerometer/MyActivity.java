package com.nicolashahn.backgroundaccelerometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*
    BackgroundAccelerometer
    Nicolas Hahn
    - Android application to record accelerometer data to log file
    - some code in onSensorChanged() from black4ninja's 'Accelerometer' app
*/
public class MyActivity extends Activity{
//    static final String LOG_TAG = MyActivity.class.getSimpleName();

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, BackgroundAccelerometerService.class));
    }

    protected void onResume() {
        super.onResume();
    }
    protected void onPause() {
        super.onPause();
    }
    protected void onDestroy() {
        super.onDestroy();
    }
}
