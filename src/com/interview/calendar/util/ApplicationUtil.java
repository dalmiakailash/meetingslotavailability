package com.interview.calendar.util;

public final class ApplicationUtil {

	private ApplicationUtil() {
	}

	public static int addMinutesInHour(final int time, final int minutes) {
		final int hour = time / 100;
		final int min = time % 100;
		final int calculatedMinutes = min + minutes;
		final int numberOfHoursInCalculatedMinutes = calculatedMinutes / 60;
		final int numberOfMinutesInCalculatedMinutes = calculatedMinutes % 60;
		return (hour + numberOfHoursInCalculatedMinutes) * 100 + numberOfMinutesInCalculatedMinutes;
	}
}
