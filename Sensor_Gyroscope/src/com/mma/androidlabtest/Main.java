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
    private Sensor sensorGyroscope;
    private TextView tv_output;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main);
        tv_output = (TextView)findViewById (R.id.tv_output);
        sensorManager = (SensorManager)this.getSystemService (SENSOR_SERVICE);
        sensorGyroscope = sensorManager.getDefaultSensor (Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume() {
		sensorManager.registerListener (this, sensorGyroscope, SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
    }

    protected void onPause() {
		sensorManager.unregisterListener (this, sensorGyroscope);
		super.onPause();
    }

	public void onAccuracyChanged (Sensor sensor, int accuracy) {
	}

	public void onSensorChanged (SensorEvent event) {
		tv_output.setText ("0: " + event.values [0] + "\n" + "1: " + event.values [1] + "\n" + "2: " + event.values [2] + "\n");
	}
}
