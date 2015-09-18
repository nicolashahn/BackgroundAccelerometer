package com.nicolashahn.backgroundaccelerometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.FileWriter;

/*
    BackgroundAccelerometer
    Nicolas Hahn
    - Android application to record accelerometer data to log file
    - Runs in background, restarts itself at bootup
*/
public class BackgroundAccelerometerService extends Service implements SensorEventListener{
    static final String LOG_TAG = MyActivity.class.getSimpleName();
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

//    private String filepath = "/sdcard/Documents/accel_log.txt";
    private String filepath;
    private FileWriter writer;
    private FileOutputStream output;

    // epoch time since last file write
    private long lastTime = 0;
    // minimum time in seconds to write to file after previous write
    private int period = 5;

    public BackgroundAccelerometerService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filepath = this.getString(R.string.file_path);
        try {
            output = new FileOutputStream(filepath, true);
            writer = new FileWriter(output.getFD());
        }catch(Exception e){
            e.printStackTrace();
            Log.e(LOG_TAG,"could not open file for writing, error "+e.toString());
        }
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.d("Service Started","Service Started");
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        Log.d("Service Destroyed", "Service Destroyed");
        try {
            writer.close();
            output.getFD().sync();
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        mSensorManager.unregisterListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        Log.e(LOG_TAG, "onAccuracyChanged called");
    }

    public void onSensorChanged(SensorEvent event) {
//        Log.e(LOG_TAG,"onSensorChanged called");
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (!mInitialized) mInitialized = true;
        long tsLong = System.currentTimeMillis()/1000;
        if (tsLong > lastTime+period) {
            lastTime = tsLong;
//            Log.e(LOG_TAG,"calling recordAccelData()");
            recordAccelData(x, y, z, tsLong);
        }
    }

    // write to file a line in format:
    // epochtime, x, y, z
    public void recordAccelData(float x, float y, float z, Long tsLong){
        String ts = tsLong.toString();
        String accelLine = ts+", "+Float.toString(x)+", "+Float.toString(y)+", "+Float.toString(z)+"\n";
        try {
            Log.e(LOG_TAG, "writing to file " + accelLine);
            writer.write(accelLine);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "exception when writing file in recordAccelData");
        }
    }
}
