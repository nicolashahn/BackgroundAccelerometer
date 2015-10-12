package com.nicolashahn.backgroundaccelerometer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/*
    BackgroundAccelerometer
    Nicolas Hahn
    - Android application to record accelerometer data to log file
    - Runs in background, restarts itself at bootup
*/
public class MyActivity extends Activity{
    static final String LOG_TAG = MyActivity.class.getSimpleName();

    private String filepath;

    public static final String FILE_PATH = "filepath";

    @Override

    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my);
        super.onCreate(savedInstanceState);
        filepath = this.getString(R.string.default_file_path);
        EditText fp = (EditText) findViewById(R.id.filePathText);
        Log.e(LOG_TAG, filepath);
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

    public void onPressStartService(View v){
        // get user chosen filepath
        EditText fp = (EditText) findViewById(R.id.filePathText);
        String userFilepath = fp.getText().toString();
        // store in sharedPrefs
        SharedPreferences prefs = this.getSharedPreferences(
                "com.nicolashahn.backgroundaccelerometer", Context.MODE_PRIVATE);
        prefs.edit().putString("filepath",userFilepath).apply();
        // pass this filepath to the service
        Intent intent = new Intent(this, BackgroundAccelerometerService.class);
//        intent.putExtra("userFilepath",userFilepath);
        Log.e(LOG_TAG, "in onPressStartService, userFilePath is "+userFilepath);
//        Bundle bundle = new Bundle();
//        bundle.putString("userFilepath",userFilepath);
        startService(intent);
        Log.e(LOG_TAG, "Started service through onPressStartService");
    }

    public void onPressStopService(View v){
        stopService(new Intent(getApplicationContext(), BackgroundAccelerometerService.class));
    }
}
