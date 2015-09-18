package com.nicolashahn.backgroundaccelerometer;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.FileWriter;

/*
    BackgroundAccelerometer
    Nicolas Hahn
    - Android application to record accelerometer data to log file
    - some code in onSensorChanged() from black4ninja's 'Accelerometer' app
*/
public class MyActivity extends Activity{
//        implements SensorEventListener{
    static final String LOG_TAG = MyActivity.class.getSimpleName();
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private String filepath = "/sdcard/Documents/accel_log.txt";
    private FileWriter writer;
    private FileOutputStream output;

    // epoch time since last file write
    private long lastTime = 0;
    // minimum time in seconds to write to file after previous write
    private int period = 5;

    private final float NOISE = (float) 0.75;

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, BackgroundAccelerometerService.class));
//        Context context = getApplicationContext();
//        try {
//            output = new FileOutputStream(filepath, true);
//            writer = new FileWriter(output.getFD());
////            writer.write("hey");
////            writer.flush();
//        }catch(Exception e){
//            e.printStackTrace();
//            Log.e(LOG_TAG,"could not open file for writing, error "+e.toString());
//        }
//        setContentView(R.layout.activity_my);
//        mInitialized = false;
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onResume() {
        super.onResume();
//        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause() {
        super.onPause();
//        mSensorManager.unregisterListener(this);
    }
    protected void onDestroy() {
        try {
            writer.close();
            output.getFD().sync();
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        TextView tvX= (TextView)findViewById(R.id.x_axis);
//        TextView tvY= (TextView)findViewById(R.id.y_axis);
//        TextView tvZ= (TextView)findViewById(R.id.z_axis);
//        ImageView iv = (ImageView)findViewById(R.id.image);
//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];
//        if (!mInitialized) {
//            mLastX = x;
//            mLastY = y;
//            mLastZ = z;
//            tvX.setText("0.0");
//            tvY.setText("0.0");
//            tvZ.setText("0.0");
//            mInitialized = true;
//        } else {
//            float deltaX = Math.abs(mLastX - x);
//            float deltaY = Math.abs(mLastY - y);
//            float deltaZ = Math.abs(mLastZ - z);
//            if (deltaX < NOISE) deltaX = (float)0.0;
//            if (deltaY < NOISE) deltaY = (float)0.0;
//            if (deltaZ < NOISE) deltaZ = (float)0.0;
//            mLastX = x;
//            mLastY = y;
//            mLastZ = z;
//            tvX.setText(Float.toString(deltaX));
//            tvY.setText(Float.toString(deltaY));
//            tvZ.setText(Float.toString(deltaZ));
//            iv.setVisibility(ImageView.VISIBLE);
//            if (deltaX > deltaY) {
//                iv.setImageResource(R.drawable.shaker_fig_1);
//            } else if (deltaY > deltaX) {
//                iv.setImageResource(R.drawable.shaker_fig_2);
//            } else {
//                iv.setVisibility(View.INVISIBLE);
//            }
//        }
//        long tsLong = System.currentTimeMillis()/1000;
//        if (tsLong > lastTime+period) {
//            lastTime = tsLong;
//            recordAccelData(x, y, z, tsLong);
//        }
//    }
//
//    // write to file:
//    // time, x, y, z
//    public void recordAccelData(float x, float y, float z, Long tsLong){
//        String ts = tsLong.toString();
//        String accelLine = ts+", "+Float.toString(x)+", "+Float.toString(y)+", "+Float.toString(z)+"\n";
//        try {
//            Log.e(LOG_TAG, "writing to file " + accelLine);
//            writer.write(accelLine);
//            writer.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e(LOG_TAG, "exception when writing file in recordAccelData");
//        }
//    }
}
