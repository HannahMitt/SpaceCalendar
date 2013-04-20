package com.spaceappsto.spacecalendar.network;

public abstract class SpaceDataListener<Type> {
	
	public abstract void onComplete(Type result);
}
