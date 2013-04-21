package com.spaceappsto.spacecalendar.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RaDecUtility {

	/**
	 * Parse Dec from its time format
	 * 
	 * @param decString
	 *            the Dec string
	 * @return double representing the radian angle of the measure
	 */
	public static double parseDecToRadians(String decString) {
		if (decString.matches("^([0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9])s$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9])s$");
			Matcher matcher = pattern.matcher(decString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));

		}

		if (decString.matches("^([0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$");
			Matcher matcher = pattern.matcher(decString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));

		}

		if (decString.matches("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$");
			Matcher matcher = pattern.matcher(decString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (decString.matches("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$");
			Matcher matcher = pattern.matcher(decString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (decString.matches("^([0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+)d$");
			Matcher matcher = pattern.matcher(decString);
			return getRadiansByDegrees(Double.parseDouble(matcher.group(1)));
		}
		if (decString.matches("^([0-9]+\\.[0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+\\.[0-9]+)d$");
			Matcher matcher = pattern.matcher(decString);
			return getRadiansByDegrees(Double.parseDouble(matcher.group(1)));
		}

		return -1;

	}

	/**
	 * Parse RA from its time format
	 * 
	 * @param raString
	 *            the RA string
	 * @return double representing the radian angle of the measure
	 */
	public static double parseRAToRadians(String raString) {
		if (raString.matches("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9])s$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9])s$");
			Matcher matcher = pattern.matcher(raString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));

		}

		if (raString.matches("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$");
			Matcher matcher = pattern.matcher(raString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));

		}

		if (raString.matches("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$");
			Matcher matcher = pattern.matcher(raString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (raString.matches("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$");
			Matcher matcher = pattern.matcher(raString);
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (raString.matches("^([0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+)d$");
			Matcher matcher = pattern.matcher(raString);
			return getRadiansByDegrees(Double.parseDouble(matcher.group(1)));
		}
		if (raString.matches("^([0-9]+\\.[0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+\\.[0-9]+)d$");
			Matcher matcher = pattern.matcher(raString);
			return getRadiansByDegrees(Double.parseDouble(matcher.group(1)));
		}

		return -1;

	}

	private static double getRadiansByDegrees(double degrees) {
		return Math.toRadians(degrees);
	}

	private static double getRadiansByHHMMSS(double hours, double minutes, double seconds) {
		double degreeHours = hours * 15.0;
		double degreesMin = (minutes / 60.0) * 15.0;
		double degreesSec = (seconds / 3600.0) * 15.0;
		double degrees = degreeHours + degreesMin + degreesSec;
		return Math.toRadians(degrees);
	}

}
