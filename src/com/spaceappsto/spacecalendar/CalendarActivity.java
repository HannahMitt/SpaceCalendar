package com.spaceappsto.spacecalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.spaceappsto.spacecalendar.network.FetchSatellitesTask;
import com.spaceappsto.spacecalendar.network.Satellite;
import com.spaceappsto.spacecalendar.network.SpaceDataListener;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;

public class CalendarActivity extends Activity {
  private static final String TAG = "SampleTimesSquareActivity";

  private ProgressDialog progressDialog;
  private SpaceDataListener<List<Satellite>> satelliteListener;
  private List<Satellite> satellites;
  
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.calendar_picker);
    
    progressDialog = new ProgressDialog(this);
    satelliteListener = new SpaceDataListener<List<Satellite>>() {
		
		@Override
		public void onComplete(List<Satellite> result) {
			satellites = result;
			Log.d("Han", "sat length: " + satellites.size());
		}
	};
	
    new FetchSatellitesTask(this, progressDialog, satelliteListener).execute();
    

    Calendar startDate = Calendar.getInstance();
    startDate.add(Calendar.YEAR, -2);
    
    Calendar futureEnd = Calendar.getInstance();
    futureEnd.add(Calendar.YEAR, 2);

    final CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
    calendar.init(new Date(), startDate.getTime(), futureEnd.getTime());
    
    calendar.setOnDateSelectedListener(new OnDateSelectedListener() {
		
		@Override
		public void onDateSelected(Date date) {
			Log.d(TAG, "Selected time in millis: " + calendar.getSelectedDate().getTime());
			startActivity(new Intent(CalendarActivity.this, DayActivity.class));
		}
	});

  }
}
