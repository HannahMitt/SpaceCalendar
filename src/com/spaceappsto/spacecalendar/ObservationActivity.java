package com.spaceappsto.spacecalendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.network.Observation;

public class ObservationActivity extends Activity {

	public static final String BUNDLE_KEY = "key_obs";
	
	private Observation observation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.observation_view);
		
		observation = getIntent().getParcelableExtra(BUNDLE_KEY);
		
		((TextView)findViewById(R.id.target_name)).setText(observation.target.name);
		
		String raDec = "RA: " + observation.target.ra + " / DEC: " + observation.target.dec;
		((TextView)findViewById(R.id.ra_dec)).setText(raDec);
		
		((TextView)findViewById(R.id.revolution)).setText("Revolution: " + observation.revolution);
		((TextView)findViewById(R.id.time_window)).setText(observation.startTime + " - " + observation.finishTime);
		((TextView)findViewById(R.id.satellite)).setText(observation.satellite.name);
	}
	
	public void onSimbadClick(View view){
		String url = "http://simbad.u-strasbg.fr/simbad/sim-id?Ident=" + observation.target.name;
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
