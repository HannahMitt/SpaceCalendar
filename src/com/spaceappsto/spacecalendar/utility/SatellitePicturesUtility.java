package com.spaceappsto.spacecalendar.utility;

import com.spaceappsto.spacecalendar.R;

public class SatellitePicturesUtility {

	public static int getResourceIdForSatellite(String name) {
		if ("Chandra".equalsIgnoreCase(name)) {
			return R.drawable.chandra;
		} else if ("INTEGRAL".equalsIgnoreCase(name)) {
			return R.drawable.integral;
		} else if ("NuSTAR".equalsIgnoreCase(name)) {
			return R.drawable.nustar;
		} else if ("RXTE".equalsIgnoreCase(name)) {
			return R.drawable.rxte;
		} else if ("suzaku".equalsIgnoreCase(name)) {
			return R.drawable.suzaku;
		} else if("Hubble Space Telescope".equalsIgnoreCase(name)){ 
			return R.drawable.hubble;
		} else if("XMM-Newton".equalsIgnoreCase(name)){
			return R.drawable.xmmnewton;
		} else {
			return R.drawable.space_background;
		}
	}
}
