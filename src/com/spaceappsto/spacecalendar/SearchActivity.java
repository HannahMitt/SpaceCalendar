package com.spaceappsto.spacecalendar;

import com.spaceappsto.spacecalendar.adapter.SearchAdapter;
import com.spaceappsto.spacecalendar.network.Satellite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		ListView listView = (ListView) findViewById(R.id.listview);
		SearchAdapter searchAdapter = new SearchAdapter(this, 0, 0, CalendarActivity.satellites);
		//listView.setAdapter(searchAdapter);
		listView.setAdapter(new ArrayAdapter<Satellite>(this, R.layout.search_cell, R.id.name, CalendarActivity.satellites));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				startActivity(new Intent(SearchActivity.this, DayActivity.class));
			}
		});
	}
}
