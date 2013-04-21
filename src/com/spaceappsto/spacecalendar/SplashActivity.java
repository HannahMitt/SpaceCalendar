package com.spaceappsto.spacecalendar;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.spaceappsto.spacecalendar.network.FetchObservationsTask;
import com.spaceappsto.spacecalendar.network.ObservationsHolder;
import com.spaceappsto.spacecalendar.network.ObservationsListener;
import com.squareup.timessquare.objects.Observation;
import com.squareup.timessquare.objects.Satellite;

public class SplashActivity extends Activity {

	//private static final int SPLASH_DURATION = 500; //0.5 seconds
	
	private ProgressDialog progressDialog;
	private ObservationsListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Beaming from space...");
		listener = new ObservationsListener() {
			
			@Override
			public void onComplete(List<Observation> observations, List<Satellite> satellites) {
				ObservationsHolder.populateObservations(observations);
				ObservationsHolder.populateSatellites(satellites);
				finish();
				startActivity(new Intent(SplashActivity.this, CalendarActivity.class));
			}
		};
		
		new FetchObservationsTask(this, progressDialog, listener).execute();
		
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				finish();
//				Intent intent = new Intent(SplashActivity.this, CalendarActivity.class);
//				SplashActivity.this.startActivity(intent);
//			}
//		}, SPLASH_DURATION);
	}
}
