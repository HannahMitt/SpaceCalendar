package com.spaceappsto.spacecalendar.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RaDecUtility {

	public static boolean compareRadians(double val1, double val2) {
		return Math.abs(val1 - val2) < 1e-2;
	}

	/**
	 * Parse Dec from its time format
	 * 
	 * @param raString
	 *            the Dec string
	 * @return double representing the radian angle of the measure
	 */
	public static double parseDecToRadians(String raString) throws IllegalStateException {
//		if (raString.matches("^([\\+-]?[0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9])s$")) {
//			Pattern pattern = Pattern.compile("^([\\+-]?[0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9])s$");
//			Matcher matcher = pattern.matcher(raString);
//			matcher.find();
//			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
//
//		}
//
//		if (raString.matches("^([\\+-]?[0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$")) {
//			Pattern pattern = Pattern.compile("^([0-9]?[0-9])d([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$");
//			Matcher matcher = pattern.matcher(raString);
//			matcher.find();
//			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
//
//		}

		if (raString.matches("^([\\+-]?[0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$")) {
			Pattern pattern = Pattern.compile("^([\\+-]?[0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (raString.matches("^([\\+-]?[0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$")) {
			Pattern pattern = Pattern.compile("^([\\+-]?[0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (raString.matches("^([\\+-]?[0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+)d$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return Math.toRadians(Double.parseDouble(matcher.group(1)));
		}
		if (raString.matches("^([\\+-]?[0-9]+\\.[0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([\\+-]?[0-9]+\\.[0-9]+)d$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return Math.toRadians(Double.parseDouble(matcher.group(1)));
		}

		throw new IllegalStateException("No matching format found");
	}

	/**
	 * Parse RA from its time format
	 * 
	 * @param raString
	 *            the RA string
	 * @return double representing the radian angle of the measure
	 */
	public static double parseRAToRadians(String raString) throws IllegalStateException {
		// if (raString.matches("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9])s$"))
		// {
		// Pattern pattern =
		// Pattern.compile("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9])s$");
		// Matcher matcher = pattern.matcher(raString);
		// matcher.find();
		// return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)),
		// Double.parseDouble(matcher.group(2)),
		// Double.parseDouble(matcher.group(3)));
		//
		// }
		//
		// if
		// (raString.matches("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$"))
		// {
		// Pattern pattern =
		// Pattern.compile("^([0-9]?[0-9])h([0-9]?[0-9])m([0-9]?[0-9]\\.[0-9]+)s$");
		// Matcher matcher = pattern.matcher(raString);
		// matcher.find();
		// return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)),
		// Double.parseDouble(matcher.group(2)),
		// Double.parseDouble(matcher.group(3)));
		//
		// }

		if (raString.matches("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])(.[0-9]+)$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (raString.matches("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$")) {
			Pattern pattern = Pattern.compile("^([0-9]?[0-9]):([0-9]?[0-9]):([0-9]?[0-9])$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return getRadiansByHHMMSS(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
		}

		if (raString.matches("^([0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+)d$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return getRadiansByDegrees(Double.parseDouble(matcher.group(1)));
		}
		if (raString.matches("^([0-9]+\\.[0-9]+)d$")) {
			Pattern pattern = Pattern.compile("^([0-9]+\\.[0-9]+)d$");
			Matcher matcher = pattern.matcher(raString);
			matcher.find();
			return getRadiansByDegrees(Double.parseDouble(matcher.group(1)));
		}

		throw new IllegalStateException("No matching format found");

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
