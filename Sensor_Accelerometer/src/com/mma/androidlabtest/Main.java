package com.mma.androidlabtest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Main extends Activity 
			 implements SensorEventListener {
			 
	private SensorManager sensorManager;
	private Sensor sensorAccelerometer;
	WindowManager windowManager;
	private TextView et_output;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		et_output = (TextView) findViewById(R.id.et_output);
		windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
	}

	protected void onResume() {
		sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}

	protected void onPause() {
		sensorManager.unregisterListener(this, sensorAccelerometer);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		et_output.setText("x: " + event.values[0] + ", y: " + event.values[1] + ", z: " + event.values[2] + ", rotation: " + windowManager.getDefaultDisplay().getRotation());
	}
}
