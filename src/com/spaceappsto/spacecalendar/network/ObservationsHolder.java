package com.spaceappsto.spacecalendar.network;

import java.util.ArrayList;
import java.util.List;

import com.squareup.timessquare.objects.Observation;
import com.squareup.timessquare.objects.Satellite;

public class ObservationsHolder {

	private static List<Observation> observations;
	private static List<Satellite> satellites;
	
	public static void populateObservations(List<Observation> obs){
		observations = obs;
	}
	
	public static void populateSatellites(List<Satellite> sats){
		satellites = sats;
	}
	
	public static List<Observation> getObservations(){
		if(observations == null){
			observations = new ArrayList<Observation>();
		}
		
		return observations;
	}
	
	public static List<Satellite> getSatellites(){
		if(satellites == null){
			satellites = new ArrayList<Satellite>();
		}
		
		return satellites;
	}
}
