package com.spaceappsto.spacecalendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.adapter.SearchAdapter;
import com.spaceappsto.spacecalendar.network.ObservationsHolder;

public class SearchActivity extends Activity {

	private Dialog coordsDialog;
	private TextView ra_text;
	private TextView dec_text;
	private String ra = "00:00:00.00";
	private String dec = "+000:00:00.00";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		setUpRaDec();
		setUpListView();
		setUpDialog();
	}

	private void setUpRaDec(){
		ra_text = (TextView) findViewById(R.id.ra_text);
		dec_text = (TextView) findViewById(R.id.dec_text);
	}
	
	private void setUpListView() {
		ListView listView = (ListView) findViewById(R.id.listview);
		SearchAdapter searchAdapter = new SearchAdapter(this, 0, 0, ObservationsHolder.getObservations());
		listView.setAdapter(searchAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(SearchActivity.this, ObservationActivity.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable(ObservationActivity.BUNDLE_KEY, ObservationsHolder.getObservations().get(arg2));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void setUpDialog() {
		coordsDialog = new Dialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
		coordsDialog.setContentView(R.layout.coords_dialog);
		coordsDialog.findViewById(R.id.dialog_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ra = ((EditText)coordsDialog.findViewById(R.id.ra_1)).getText().toString() + ":";
				ra += ((EditText)coordsDialog.findViewById(R.id.ra_2)).getText().toString() + ":";
				ra += ((EditText)coordsDialog.findViewById(R.id.ra_3)).getText().toString() + ".";
				ra += ((EditText)coordsDialog.findViewById(R.id.ra_4)).getText().toString();
				
				dec = ((EditText)coordsDialog.findViewById(R.id.dec_1)).getText().toString() + ":";
				dec += ((EditText)coordsDialog.findViewById(R.id.dec_2)).getText().toString() + ":";
				dec += ((EditText)coordsDialog.findViewById(R.id.dec_3)).getText().toString() + ".";
				dec += ((EditText)coordsDialog.findViewById(R.id.dec_4)).getText().toString();
				
				ra_text.setText("RA " + ra);
				dec_text.setText("DEC " + dec);
				coordsDialog.dismiss();
			}
		});
	}

	public void onClickEditSearch(View view) {
		coordsDialog.show();
	}
}
