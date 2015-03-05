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
    private Sensor sensorMagneticField;
    private TextView tv_output;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main);
        sensorManager = (SensorManager)this.getSystemService (SENSOR_SERVICE);
        sensorMagneticField = sensorManager.getDefaultSensor (Sensor.TYPE_MAGNETIC_FIELD);
        tv_output = (TextView)findViewById (R.id.tv_output);
    }

    protected void onResume() {
		sensorManager.registerListener (this, sensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
    }

    protected void onPause() {
		sensorManager.unregisterListener (this, sensorMagneticField);
		super.onPause();
    }

	public void onAccuracyChanged (Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		tv_output.setText ("X: " + event.values [0] + "\nY: " + event.values [1] + "\nZ: " + event.values [2]);
	}
}
