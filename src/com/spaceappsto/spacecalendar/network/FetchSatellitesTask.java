package com.spaceappsto.spacecalendar.network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spaceappsto.spacecalendar.R;

public class FetchSatellitesTask extends AsyncTask<Void, Void, List<Satellite>> {

	private Context context;
	private ProgressDialog progressDialog;
	private SpaceDataListener<List<Satellite>> listener;

	public FetchSatellitesTask(Context context, ProgressDialog progressDialog, SpaceDataListener<List<Satellite>> listener) {
		this.context = context;
		this.progressDialog = progressDialog;
		this.listener = listener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.show();
	}

	@Override
	protected List<Satellite> doInBackground(Void... arg0) {
		Gson gson = new Gson();
		
		InputStream stream = context.getResources().openRawResource(R.raw.satellites);
		Reader reader = new InputStreamReader(stream);
		
		List<Satellite> satellites = gson.fromJson(reader, new TypeToken<List<Satellite>>() {}.getType());
		
		return satellites;
	}

	@Override
	protected void onPostExecute(List<Satellite> result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
		listener.onComplete(result);
	}

}
