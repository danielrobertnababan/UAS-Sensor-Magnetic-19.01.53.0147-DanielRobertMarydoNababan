package com.example.magnetic;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

 private SensorManager mSensorManager;
 private Sensor mMagnetometer;
 private TextView axisX, axisY, axisZ;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  axisX = (TextView) findViewById(R.id.mag_x);
  axisY = (TextView) findViewById(R.id.mag_y);
  axisZ = (TextView) findViewById(R.id.mag_z);

  mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
  mMagnetometer= mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
 }

 @Override
 public void onAccuracyChanged(Sensor sensor, int accuracy) {
   // apabila ingin menambahkan nilai akurasi sensor.
 }

 @Override
 public void onSensorChanged(SensorEvent event) {
  float x = event.values[0];
     float y = event.values[1];
     float z = event.values[2];

     axisX.setText("X: "+x);
     axisY.setText("Y: "+y);
     axisZ.setText("Z: "+z);
 }

 @Override
   protected void onResume() {
     super.onResume();
     mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
   }

   @Override
   protected void onPause() {
     super.onPause();
     mSensorManager.unregisterListener(this);
   }
}
