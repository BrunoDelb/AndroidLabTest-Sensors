package com.mma.androidlabtest;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {
			 
    private HashMap<Integer, String> sensorTypes;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView etOutput = (TextView)findViewById(R.id.et_output);
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder message = new StringBuilder();
        sensorTypes = new HashMap<Integer, String>();
		sensorTypes.put(Sensor.TYPE_ACCELEROMETER, "TYPE_ACCELEROMETER"); // 1
		sensorTypes.put(Sensor.TYPE_GYROSCOPE, "TYPE_GYROSCOPE"); // 4
		sensorTypes.put(Sensor.TYPE_LIGHT, "TYPE_LIGHT"); // 5
		sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD, "TYPE_MAGNETIC_FIELD"); // 2
		sensorTypes.put(Sensor.TYPE_ORIENTATION, "TYPE_ORIENTATION"); // 3
		sensorTypes.put(Sensor.TYPE_PRESSURE, "TYPE_PRESSURE"); // 6
		sensorTypes.put(Sensor.TYPE_PROXIMITY, "TYPE_PROXIMITY"); // 8
		sensorTypes.put(Sensor.TYPE_TEMPERATURE, "TYPE_TEMPERATURE"); // 7
		sensorTypes.put(Sensor.TYPE_GRAVITY, "TYPE_GRAVITY"); // 9
		sensorTypes.put(Sensor.TYPE_LINEAR_ACCELERATION, "TYPE_LINEAR_ACCELERATION"); // 10
		sensorTypes.put(Sensor.TYPE_ROTATION_VECTOR, "TYPE_ROTATION_VECTOR"); // 11
        for(Sensor sensor : sensors) {
			message.append(sensor.getName() + "\n");
			message.append("Type: " + sensorTypes.get(sensor.getType()) + "\n");
			message.append("Vendor: " + sensor.getVendor() + "\n");
			message.append("Version: " + sensor.getVersion() + "\n");
			message.append("Resolution: " + sensor.getResolution() + "\n");
			message.append("Max Range: " + sensor.getMaximumRange() + "\n");
			message.append("Power: " + sensor.getPower() + " mA\n");
        }
        etOutput.setText(message);
    }
}
