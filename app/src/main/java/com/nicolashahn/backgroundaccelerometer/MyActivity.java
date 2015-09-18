package com.nicolashahn.backgroundaccelerometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/*
    BackgroundAccelerometer
    Nicolas Hahn
    - Android application to record accelerometer data to log file
    - Runs in background, restarts itself at bootup
*/
public class MyActivity extends Activity{
    static final String LOG_TAG = MyActivity.class.getSimpleName();

    private String filepath;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my);
        super.onCreate(savedInstanceState);
        filepath = this.getString(R.string.file_path);
        startService(new Intent(this, BackgroundAccelerometerService.class));
        TextView fp = (TextView) findViewById(R.id.filePathText);
        Log.e(LOG_TAG, filepath );
        fp.setText(filepath);
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
