package com.spaceappsto.spacecalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.network.FetchSatellitesTask;
import com.spaceappsto.spacecalendar.network.Satellite;
import com.spaceappsto.spacecalendar.network.SpaceDataListener;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.DotUtility;

public class CalendarActivity extends Activity {
	private static final String TAG = "SampleTimesSquareActivity";

	private ProgressDialog progressDialog;
	private SpaceDataListener<List<Satellite>> satelliteListener;
	private List<Satellite> satellites;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_picker);

		progressDialog = new ProgressDialog(this);
		satelliteListener = new SpaceDataListener<List<Satellite>>() {

			@Override
			public void onComplete(List<Satellite> result) {
				satellites = result;
				populateLegend();
			}
		};
		
		populateCalendar();

		new FetchSatellitesTask(this, progressDialog, satelliteListener).execute();
	}

	

	private void populateLegend() {
		int numPerCol = (int) Math.ceil(satellites.size() / 3.0);

		LinearLayout col1 = (LinearLayout) findViewById(R.id.legend_col1);
		fillColumn(numPerCol, 0, col1);

		LinearLayout col2 = (LinearLayout) findViewById(R.id.legend_col2);
		fillColumn(numPerCol, 1, col2);

		LinearLayout col3 = (LinearLayout) findViewById(R.id.legend_col3);
		fillColumn(numPerCol, 2, col3);
	}

	private void fillColumn(int numPerCol, int colIndex, LinearLayout col) {
		for (int i = 0; i < numPerCol; i++) {
			if (satellites.size() > numPerCol * colIndex + i) {
				Satellite satellite = satellites.get(numPerCol * colIndex + i);
				TextView legendLabel = (TextView) getLayoutInflater().inflate(R.layout.legend_text, null);
				legendLabel.setText(satellite.name);
				
				Drawable drawable = DotUtility.getDotWithColorIndex(this, satellite.id);
				legendLabel.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
				col.addView(legendLabel);
			} else
				break;
		}
	}
	
	private void populateCalendar() {
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
