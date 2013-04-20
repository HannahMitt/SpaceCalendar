package com.spaceappsto.spacecalendar;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;

public class SampleTimesSquareActivity extends Activity {
  private static final String TAG = "SampleTimesSquareActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.calendar_picker);

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
			startActivity(new Intent(SampleTimesSquareActivity.this, DayActivity.class));
		}
	});

  }
}
