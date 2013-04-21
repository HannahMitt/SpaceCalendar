package com.spaceappsto.spacecalendar.network;

import java.util.List;

import com.squareup.timessquare.objects.Observation;
import com.squareup.timessquare.objects.Satellite;

public abstract class ObservationsListener {
	
	public abstract void onComplete(List<Observation> observations, List<Satellite> satellites);
}
