package com.spaceappsto.spacecalendar;

import com.spaceappsto.spacecalendar.adapter.SearchAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		ListView listView = (ListView) findViewById(R.id.listview);
		SearchAdapter searchAdapter = new SearchAdapter(this, 0, 0, CalendarActivity.satellites);
		listView.setAdapter(searchAdapter);
	}
}
