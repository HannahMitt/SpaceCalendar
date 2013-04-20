package com.spaceappsto.spacecalendar.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spaceappsto.spacecalendar.R;
import com.spaceappsto.spacecalendar.network.Satellite;

public class SearchAdapter extends ArrayAdapter<Satellite> {

	private Context context;
	private List<Satellite> satellites;
	
	public SearchAdapter(Context context, int resource, int textViewResourceId, List<Satellite> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
		satellites = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
        
        if(row == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.search_cell, parent, false);
            
        }

        Satellite satellite = satellites.get(position);
        ((TextView)row.findViewById(R.id.name)).setText(satellite.name);
        
        return row;
	}
}
