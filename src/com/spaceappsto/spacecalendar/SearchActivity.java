package com.spaceappsto.spacecalendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.spaceappsto.spacecalendar.adapter.SearchAdapter;
import com.spaceappsto.spacecalendar.network.ObservationsHolder;
import com.squareup.timessquare.objects.Observation;

public class SearchActivity extends Activity {

	private Dialog coordsDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		setUpListView();
		setUpDialog();
	}

	private void setUpListView() {
		ListView listView = (ListView) findViewById(R.id.listview);
		SearchAdapter searchAdapter = new SearchAdapter(this, 0, 0, ObservationsHolder.getObservations());
		// listView.setAdapter(searchAdapter);
		listView.setAdapter(new ArrayAdapter<Observation>(this, R.layout.search_cell, R.id.name, ObservationsHolder.getObservations()));
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
	}

	public void onClickEditSearch(View view) {
		coordsDialog.show();
	}
}
