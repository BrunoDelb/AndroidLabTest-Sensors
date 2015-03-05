package com.mma.androidlabtest;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class Main extends Activity implements LocationListener {

	EditText et_status;
	EditText et_position;
	LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		et_status = (EditText)findViewById (R.id.et_status);
		et_position = (EditText)findViewById (R.id.et_position);
		locationManager = (LocationManager)getSystemService (Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		et_position.setText("Latitude: " + location.getLatitude() + ", longitude: " + location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		et_status.setText("Disabled");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		et_status.setText("Enabled");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
