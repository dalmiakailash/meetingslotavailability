package com.interview.calendar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class MeetingsCalendar {
	private final List<TimeSlot> busyTimeSlots;
	private final List<TimeSlot> freeTimeSlots;
	private final TimeSlot workingTimeSlot;

	public MeetingsCalendar(
			final List<TimeSlot> busyTimeSlots, final List<TimeSlot> freeTimeSlots, final TimeSlot workingTimeSlot) {
		this.busyTimeSlots = new ArrayList<>(Objects.requireNonNull(busyTimeSlots));
		this.freeTimeSlots = new ArrayList<>(Objects.requireNonNull(freeTimeSlots));
		this.workingTimeSlot = Objects.requireNonNull(workingTimeSlot);
	}

	public List<TimeSlot> getBusyTimeSlots() {
		return Collections.unmodifiableList(busyTimeSlots);
	}

	public List<TimeSlot> getFreeTimeSlots() {
		return Collections.unmodifiableList(freeTimeSlots);
	}

	public TimeSlot getWorkingTimeSlot() {
		return workingTimeSlot;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MeetingsCalendar)) {
			return false;
		}
		final MeetingsCalendar that = (MeetingsCalendar) o;
		return Objects.equals(getBusyTimeSlots(), that.getBusyTimeSlots()) && Objects.equals(getFreeTimeSlots(),
				that.getFreeTimeSlots()) && Objects.equals(getWorkingTimeSlot(), that.getWorkingTimeSlot());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBusyTimeSlots(), getFreeTimeSlots(), getWorkingTimeSlot());
	}

	@Override
	public String toString() {
		return "MeetingsCalendar{"
				+ "busyTimeSlots="
				+ busyTimeSlots
				+ ", freeTimeSlots="
				+ freeTimeSlots
				+ ", workingTimeSlot="
				+ workingTimeSlot
				+ '}';
	}
}
