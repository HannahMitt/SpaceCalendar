package com.spaceappsto.spacecalendar.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.R;
import com.squareup.timessquare.DotUtility;
import com.squareup.timessquare.objects.Observation;

public class SearchAdapter extends ArrayAdapter<Observation> {

	private Context context;
	private List<Observation> observations;
	private SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");

	public SearchAdapter(Context context, int resource, int textViewResourceId, List<Observation> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
		observations = objects;
	}
	
	public void setList(List<Observation> observations){
		this.observations = observations;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(R.layout.search_cell, parent, false);

		}

		Observation observation = observations.get(position);
		((TextView) row.findViewById(R.id.target_name)).setText(observation.target.name);
		row.findViewById(R.id.dot).setBackgroundDrawable(DotUtility.getDotWithColorIndex(context, observation.satellite.id));
		((TextView) row.findViewById(R.id.satellite_name)).setText("Satellite: " + observation.satellite.name);
		((TextView) row.findViewById(R.id.date)).setText("Date: " + sdf.format(observation.start_time));

		return row;
	}
}
