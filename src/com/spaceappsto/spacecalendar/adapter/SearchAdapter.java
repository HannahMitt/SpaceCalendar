package com.spaceappsto.spacecalendar.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.R;
import com.spaceappsto.spacecalendar.network.Observation;

public class SearchAdapter extends ArrayAdapter<Observation> {

	private Context context;
	private List<Observation> observations;
	
	public SearchAdapter(Context context, int resource, int textViewResourceId, List<Observation> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
		observations = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
        
        if(row == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.search_cell, parent, false);
            
        }

        Observation satellite = observations.get(position);
        ((TextView)row.findViewById(R.id.name)).setText(satellite.satellite.name);
        
        return row;
	}
}
