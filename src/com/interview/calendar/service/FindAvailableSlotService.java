package com.interview.calendar.service;

import com.interview.calendar.model.MeetingsCalendar;
import com.interview.calendar.model.TimeSlot;
import com.interview.calendar.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FindAvailableSlotService {

	public List<TimeSlot> findMeetableTimeSlots(
			final MeetingsCalendar firstPersonMeetingCal,
			final MeetingsCalendar secondPersonMeetingCal,
			final int meetingTimeInMin) {
		final List<TimeSlot> firstPersonAvailableTimeSlot = filterInvalidTimeSlots(firstPersonMeetingCal.getFreeTimeSlots(),
				meetingTimeInMin);
		final List<TimeSlot> secondPersonAvailableTimeSlot = filterInvalidTimeSlots(secondPersonMeetingCal.getFreeTimeSlots(),
				meetingTimeInMin);
		if (anyOfTimeSlotsEmpty(firstPersonAvailableTimeSlot, secondPersonAvailableTimeSlot)) {
			return Collections.emptyList();
		}
		final List<TimeSlot> possibleMeetingSlots = new ArrayList<>();
		final int firstPersonMeetingSlots = firstPersonAvailableTimeSlot.size();
		final int secondPersonMeetingSlots = secondPersonAvailableTimeSlot.size();
		if (firstPersonMeetingSlots < secondPersonMeetingSlots) {
			fillInPossibleTimeSlots(meetingTimeInMin,
					firstPersonAvailableTimeSlot,
					secondPersonAvailableTimeSlot,
					possibleMeetingSlots);
		} else {
			fillInPossibleTimeSlots(meetingTimeInMin,
					secondPersonAvailableTimeSlot,
					firstPersonAvailableTimeSlot,
					possibleMeetingSlots);
		}
		return possibleMeetingSlots;
	}

	private void fillInPossibleTimeSlots(
			final int meetingTimeInMin,
			final List<TimeSlot> firstCalendarFreeSlots,
			final List<TimeSlot> secondCalendarFreeSlots,
			final List<TimeSlot> possibleMeetingSlots) {
		int possibleMeetingSlotIdx = 0;
		final int secondPersonMeetingSlots = secondCalendarFreeSlots.size();
		for (TimeSlot timeSlot : firstCalendarFreeSlots) {
			for (int j = possibleMeetingSlotIdx; j < secondPersonMeetingSlots; j++) {
				if (isValidTimeSlotRange(timeSlot, secondCalendarFreeSlots.get(j))) {
					possibleMeetingSlotIdx = j + 1;
					possibleMeetingSlots.addAll(fetchPossibleTimeSlotsInBetween(timeSlot,
							secondCalendarFreeSlots.get(j),
							meetingTimeInMin));
				}
			}
		}
	}

	private List<TimeSlot> fetchPossibleTimeSlotsInBetween(
			final TimeSlot first, final TimeSlot second, final int meetingTimeInMin) {
		final TimeSlot timeSlot = new TimeSlot(Math.max(first.getStartHour(), second.getStartHour()),
				Math.min(first.getEndHour(), second.getEndHour()));
		final List<TimeSlot> timeSlots = new ArrayList<>();
		if (isEnoughTimeForMeeting(timeSlot, meetingTimeInMin)) {
			for (int i = timeSlot.getStartHour(); i < timeSlot.getEndHour(); ) {
				final TimeSlot slot = createTimeSlot(i, timeSlot.getEndHour(), meetingTimeInMin);
				i = createHour(i, meetingTimeInMin);
				if (Objects.nonNull(slot)) {
					timeSlots.add(slot);
				}
			}
		}
		return timeSlots;
	}

	private TimeSlot createTimeSlot(final int startHour, final int endHour, final int meetingTimeInMin) {
		final int newEndHour = createHour(startHour, meetingTimeInMin);
		if (newEndHour <= endHour) {
			return new TimeSlot(startHour, newEndHour);
		}
		return null;
	}

	private int createHour(final int time, final int meetingTimeInMin) {
		/*final int actualHour;
		if (meetingTimeInMin > 100) {
			actualHour = time + meetingTimeInMin + (meetingTimeInMin / 60) * 40;
		} else {
			actualHour = time + meetingTimeInMin;
		}
		if (actualHour % 100 == 60) {
			return actualHour + 40;
		}*/
		return ApplicationUtil.addMinutesInHour(time, meetingTimeInMin);
	}

	private boolean anyOfTimeSlotsEmpty(
			final List<TimeSlot> firstPersonAvailableTimeSlot, final List<TimeSlot> secondPersonAvailableTimeSlot) {
		return firstPersonAvailableTimeSlot.isEmpty() || secondPersonAvailableTimeSlot.isEmpty();
	}

	private List<TimeSlot> filterInvalidTimeSlots(final List<TimeSlot> freeTimeSlots, final int meetingTimeInMin) {
		final List<TimeSlot> filtered = new ArrayList<>();
		freeTimeSlots.forEach(timeSlot -> {
			if (isEnoughTimeForMeeting(timeSlot, meetingTimeInMin)) {
				filtered.add(timeSlot);
			}
		});
		return filtered;
	}

	private boolean isEnoughTimeForMeeting(final TimeSlot timeSlot, final int meetingTime) {
		int timeDiff = timeSlot.getEndHour() - timeSlot.getStartHour();
		if (timeDiff > 100) {
			timeDiff = timeDiff - (timeDiff / 100) * 40;
		}
		return timeDiff >= meetingTime;
	}

	private boolean isValidTimeSlotRange(final TimeSlot first, final TimeSlot second) {
		return areOverlapped(first, second) || areOverlapped(second, first)
				|| areEnclosed(first, second);
	}

	private boolean areOverlapped(final TimeSlot first, final TimeSlot second) {
		return first.getEndHour() >= second.getStartHour()
				&& second.getStartHour() >= first.getStartHour()
				&& second.getEndHour() >= first.getEndHour();
	}

	private boolean areEnclosed(final TimeSlot first, final TimeSlot second) {
		return first.getStartHour() <= second.getStartHour() && first.getEndHour() >= second.getEndHour();
	}
}
