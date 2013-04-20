package com.spaceappsto.spacecalendar.network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spaceappsto.spacecalendar.R;

public class FetchObservationsTask extends AsyncTask<Void, Void, List<Observation>> {

	private Context context;
	private ProgressDialog progressDialog;
	private ObservationsListener listener;
	private ArrayList<Satellite> satellites;

	public FetchObservationsTask(Context context, ProgressDialog progressDialog, ObservationsListener listener) {
		this.context = context;
		this.progressDialog = progressDialog;
		this.listener = listener;
		satellites = new ArrayList<Satellite>();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.show();
	}

	@Override
	protected List<Observation> doInBackground(Void... arg0) {
		Gson gson = new Gson();
		
		InputStream stream = context.getResources().openRawResource(R.raw.observation);
		Reader reader = new InputStreamReader(stream);
		
		List<Observation> observations = gson.fromJson(reader, new TypeToken<List<Observation>>() {}.getType());
		
		for(Observation observation: observations){
			if(!satellites.contains(observation.satellite)){
				satellites.add(observation.satellite);
			}
		}
		
		return observations;
	}

	@Override
	protected void onPostExecute(List<Observation> result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
		listener.onComplete(result, satellites);
	}

}
