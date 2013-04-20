package com.spaceappsto.spacecalendar.network;

import java.util.List;

public abstract class ObservationsListener {
	
	public abstract void onComplete(List<Observation> observations, List<Satellite> satellites);
}
