package com.spaceappsto.spacecalendar.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Satellite implements Serializable{

	private static final long serialVersionUID = -6953598619658630380L;

	public String url;
	public boolean active;
	public String name;
	public int id;

	public ArrayList<Date> dates;

	@Override
	public String toString() {
		return name + " " + super.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Satellite) {
			return ((Satellite)o).name.equalsIgnoreCase(name);
		} else {
			return super.equals(o);
		}
	}
}
