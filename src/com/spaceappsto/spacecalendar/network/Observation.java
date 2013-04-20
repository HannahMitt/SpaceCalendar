package com.spaceappsto.spacecalendar.network;

import java.util.Date;

import com.google.gson.annotations.SerializedName;


public class Observation {

	@SerializedName("finish_time")
	public Date finishTime;
	
	@SerializedName("start_time")
	public Date startTime;

	public Target target;
	public Satellite satellite;
	public int revolution;
}
