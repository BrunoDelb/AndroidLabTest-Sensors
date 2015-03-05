package com.mma.androidlabtest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
			 
	WakeLock wakeLock = null;
	int screenOffTimeout;
	TextView et_output;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		et_output = (TextView)findViewById(R.id.et_output);
		PowerManager powerManager = (PowerManager)this.getSystemService (POWER_SERVICE);
		wakeLock = powerManager.newWakeLock (PowerManager.PARTIAL_WAKE_LOCK, "Accel");
		wakeLock.acquire();
		try {
			screenOffTimeout = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
		} catch(Exception e) {
			screenOffTimeout = 120000;
		}
		Settings.System.putInt(getContentResolver(), 
		Settings.System.SCREEN_OFF_TIMEOUT, 5000);
	}

	protected void onStart() {
		IntentFilter intentFilter = new IntentFilter (Intent.ACTION_SCREEN_OFF);
		registerReceiver (broadcastReceiver, intentFilter);
		super.onStart();
	}

	protected void onStop() {
		unregisterReceiver(broadcastReceiver);
		super.onStop();
	}

	protected void onDestroy() {
		Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, screenOffTimeout);
		wakeLock.release();
		super.onDestroy();
	}
	
	public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
				Toast.makeText(getApplicationContext(), "Screen off", 5000).show();
			} else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
				Toast.makeText(getApplicationContext(), "Screen on", 5000).show();
			}
		}
	};
}
