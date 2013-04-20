package com.spaceappsto.spacecalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	private static final int SPLASH_DURATION = 500; //0.5 seconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				finish();
				Intent intent = new Intent(SplashActivity.this, CalendarActivity.class);
				SplashActivity.this.startActivity(intent);
			}
		}, SPLASH_DURATION);
	}
}
