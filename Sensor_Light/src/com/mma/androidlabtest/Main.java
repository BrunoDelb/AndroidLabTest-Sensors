package com.mma.androidlabtest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity 
			 implements SensorEventListener {
			 
    private SensorManager sensorManager;
    private Sensor sensorLight;
    private TextView tvOutput;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main);
        sensorManager = (SensorManager)this.getSystemService (SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor (Sensor.TYPE_LIGHT);
        tvOutput = (TextView)findViewById (R.id.tv_output);
    }

    protected void onResume() {
		sensorManager.registerListener (this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
    }

    protected void onPause() {
		sensorManager.unregisterListener (this, sensorLight);
		super.onPause();
    }

	public void onAccuracyChanged (Sensor sensor, int accuracy) {
		tvOutput.setText ("Accuracy changed: " + accuracy);
	}

	public void onSensorChanged (SensorEvent event) {
		tvOutput.setText ("Light: " + event.values[0] + " SI lux units");
	}
}
