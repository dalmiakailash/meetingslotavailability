package com.interview.calendar.service;

import com.interview.calendar.model.MeetingsCalendar;
import com.interview.calendar.model.TimeSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadInputCalendarService {

	public MeetingsCalendar readInput(final String[][] meetingSlots, final String[] workingHours){
		final TimeSlot workingSlot = new TimeSlot(Integer.valueOf(workingHours[0].replace(":","")),
				Integer.valueOf(workingHours[1].replace(":","")));
		final List<TimeSlot> busySlots = new ArrayList<>();
		for (String[] range:meetingSlots) {
			busySlots.add(new TimeSlot(Integer.valueOf(range[0].replace(":","")),
					Integer.valueOf(range[1].replace(":",""))));
		}
		final List<TimeSlot> freeSlots = findFreeSlots(busySlots, workingSlot);
		return new MeetingsCalendar(busySlots, freeSlots, workingSlot);
	}

	private List<TimeSlot> findFreeSlots(final List<TimeSlot> busySlots, final TimeSlot workingHours) {
		final List<TimeSlot> freeSlots = new ArrayList<>();
		if(Objects.isNull(busySlots) || busySlots.isEmpty()){
			freeSlots.add(workingHours);
			return freeSlots;
		}
		TimeSlot prevMeetingSlot = busySlots.get(0);
		if(workingHours.getStartHour() < prevMeetingSlot.getStartHour()){
			final TimeSlot firstFreeSlot = new TimeSlot(workingHours.getStartHour(), prevMeetingSlot.getStartHour());
			freeSlots.add(firstFreeSlot);
		}
		final int len = busySlots.size();
		for(int i=1; i<=len-1;i++){
			final TimeSlot currentMeetingSlot = busySlots.get(i);
			if(prevMeetingSlot.getEndHour() < currentMeetingSlot.getStartHour()){
				freeSlots.add(new TimeSlot(prevMeetingSlot.getEndHour(), currentMeetingSlot.getStartHour()));
			}
			prevMeetingSlot = currentMeetingSlot;
		}
		final TimeSlot lastMeetingOfDay = busySlots.get(len-1);
		if(lastMeetingOfDay.getEndHour() < workingHours.getEndHour()){
			freeSlots.add(new TimeSlot(lastMeetingOfDay.getEndHour(), workingHours.getEndHour()));
		}
		return freeSlots;
	}

}
