package com.spaceappsto.spacecalendar;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.utility.SatellitePicturesUtility;
import com.squareup.timessquare.DotUtility;
import com.squareup.timessquare.objects.Observation;

public class ObservationActivity extends Activity {

	public static final String BUNDLE_KEY = "key_obs";

	private Observation observation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.observation_view);

		observation = getIntent().getParcelableExtra(BUNDLE_KEY);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		((ImageView) findViewById(R.id.satellite_pic)).setImageResource(SatellitePicturesUtility.getResourceIdForSatellite(observation.satellite.name));
		TextView satText = (TextView) findViewById(R.id.satellite);
		satText.setText(observation.satellite.name);
		satText.setCompoundDrawablesWithIntrinsicBounds(DotUtility.getDotWithColorIndex(this, observation.satellite.id), null, null, null);

		((TextView) findViewById(R.id.target_name)).setText(observation.target.name);

		((TextView) findViewById(R.id.ra)).setText(observation.target.ra);
		((TextView) findViewById(R.id.dec)).setText(observation.target.dec);

		((TextView) findViewById(R.id.revolution)).setText("Revolution: " + observation.revolution);
		((TextView) findViewById(R.id.start_time)).setText(sdf.format(observation.start_time));
		((TextView) findViewById(R.id.end_time)).setText(sdf.format(observation.finish_time));
	}

	public void onSimbadClick(View view) {
		String url = "http://simbad.u-strasbg.fr/simbad/sim-id?Ident=" + observation.target.name;
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
