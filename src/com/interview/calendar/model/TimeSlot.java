package com.interview.calendar.model;

import java.util.Arrays;
import java.util.Objects;

public final class TimeSlot {
	private final int startHour;
	private final int endHour;

	public TimeSlot(final int startHour, final int endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public int getStartHour() {
		return startHour;
	}

	@Override public String toString() {
		return "["
				+ String.join(":",
				Arrays.asList(String.format("%02d", startHour / 100), String.format("%02d",startHour % 100)))
				+ ","
				+ String.join(":",
				Arrays.asList(String.format("%02d",endHour / 100), String.format("%02d",endHour % 100)))
				+ "]";
	}

	@Override public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TimeSlot)) {
			return false;
		}
		final TimeSlot timeSlot = (TimeSlot) o;
		return getStartHour() == timeSlot.getStartHour() && getEndHour() == timeSlot.getEndHour();
	}

	@Override public int hashCode() {
		return Objects.hash(getStartHour(), getEndHour());
	}
}
