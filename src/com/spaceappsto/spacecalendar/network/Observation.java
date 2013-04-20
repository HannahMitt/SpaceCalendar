package com.spaceappsto.spacecalendar.network;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Observation implements Parcelable {

	public Observation(Parcel in) {
		readFromParcel(in);
	}

	@SerializedName("finish_time")
	public Date finishTime;

	@SerializedName("start_time")
	public Date startTime;

	public Target target;
	public Satellite satellite;
	public int revolution;

	@Override
	public String toString() {
		return satellite.name + "\n" + target.name + "\n" + super.toString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void readFromParcel(Parcel in) {
		startTime = (Date) in.readSerializable();
		finishTime = (Date) in.readSerializable();
		target = (Target) in.readSerializable();
		satellite = (Satellite) in.readSerializable();
		revolution = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(startTime);
		dest.writeSerializable(finishTime);
		dest.writeSerializable(target);
		dest.writeSerializable(satellite);
		dest.writeInt(revolution);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Observation createFromParcel(Parcel in) {
			return new Observation(in);
		}

		@Override
		public Observation[] newArray(int size) {
			return new Observation[size];
		}
	};
}
