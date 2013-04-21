package com.spaceappsto.spacecalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.network.ObservationsHolder;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.DotUtility;
import com.squareup.timessquare.objects.Observation;
import com.squareup.timessquare.objects.Satellite;

public class CalendarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_picker);

		populateCalendar();
		populateLegend();
	}

	private void populateLegend() {
		int numPerCol = (int) Math.ceil(ObservationsHolder.getSatellites().size() / 3.0);

		LinearLayout col1 = (LinearLayout) findViewById(R.id.legend_col1);
		fillColumn(numPerCol, 0, col1);

		LinearLayout col2 = (LinearLayout) findViewById(R.id.legend_col2);
		fillColumn(numPerCol, 1, col2);

		LinearLayout col3 = (LinearLayout) findViewById(R.id.legend_col3);
		fillColumn(numPerCol, 2, col3);
	}

	private void fillColumn(int numPerCol, int colIndex, LinearLayout col) {
		for (int i = 0; i < numPerCol; i++) {
			if (ObservationsHolder.getSatellites().size() > numPerCol * colIndex + i) {
				Satellite satellite = ObservationsHolder.getSatellites().get(numPerCol * colIndex + i);
				TextView legendLabel = (TextView) getLayoutInflater().inflate(R.layout.legend_text, null);
				legendLabel.setText(satellite.name);
				legendLabel.setMaxLines(1);

				Drawable drawable = DotUtility.getDotWithColorIndex(this, satellite.id);
				legendLabel.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
				col.addView(legendLabel);
			} else
				break;
		}
	}

	private void populateCalendar() {
		List<Observation> obs = ObservationsHolder.getObservations();

		Calendar startDate = Calendar.getInstance();
		startDate.setTime(obs.get(0).start_time);
		startDate.set(Calendar.DAY_OF_MONTH, 1);

		Calendar futureEnd = Calendar.getInstance();
		futureEnd.setTime(obs.get(obs.size() - 1).finish_time);
		futureEnd.add(Calendar.MONTH, 2);
		futureEnd.set(Calendar.DAY_OF_MONTH, 1);
		futureEnd.add(Calendar.DAY_OF_MONTH, -1);

		final CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		calendar.init(new Date(), startDate.getTime(), futureEnd.getTime(), ObservationsHolder.getObservations());

		calendar.setOnDateSelectedListener(new OnDateSelectedListener() {

			@Override
			public void onDateSelected(Date date) {
				findObservationsForDate(date);
			}
		});
	}

	private void findObservationsForDate(Date date) {
		ArrayList<Observation> obs = new ArrayList<Observation>();
		for (Observation o : ObservationsHolder.getObservations()) {
			if (o.fallsOnDate(date)) {
				obs.add(o);
			}
		}

		if (obs.size() == 1) {
			startObservationActivity(obs.get(0));
		} else {
			showSatelliteDialog(obs);
		}
	}

	private void showSatelliteDialog(final List<Observation> obs) {
		CharSequence[] sats = new CharSequence[obs.size()];
		for (int i = 0; i < obs.size(); i++) {
			Observation o = obs.get(i);
			sats[i] = o.satellite.name + ": " + o.target.name;
		}
		new AlertDialog.Builder(this).setItems(sats, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startObservationActivity(obs.get(which));
			}
		}).show();
	}

	private void startObservationActivity(Observation o) {
		Intent intent = new Intent(CalendarActivity.this, ObservationActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(ObservationActivity.BUNDLE_KEY, o);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_calendar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_search:
			startActivity(new Intent(this, SearchActivity.class));
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
