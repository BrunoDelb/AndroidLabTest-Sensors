package com.mma.androidlabtest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity 
			 implements LocationListener {

    LocationManager locationManager;
    TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) this.findViewById(R.id.location);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000l, 5.0f, this);
	}

	public void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	public void onLocationChanged(Location location) {
		textView.setText(location.getLatitude() + " " + location.getLongitude());
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		if (status == LocationProvider.AVAILABLE) {
		} else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
		} else if (status == LocationProvider.OUT_OF_SERVICE) {
		}
	}
}
