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
    private Sensor temp;
    private TextView etOutput;
    private StringBuilder msg = new StringBuilder(2048);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        etOutput = (TextView)findViewById(R.id.et_output);
    }

    protected void onResume() {
    	sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
    }

    protected void onPause() {
    	sensorManager.unregisterListener(this, temp);
		super.onPause();
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		float fahrenheit = event.values[0] * 9 / 5 + 32;
		msg.insert(0, "Temperature: " + event.values[0] + " celsius (" + fahrenheit  + " F)");
		etOutput.setText(msg);
		etOutput.invalidate();
	}
}
