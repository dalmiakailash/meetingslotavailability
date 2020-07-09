package com.interview.calendar;

import com.interview.calendar.service.FindAvailableSlotService;
import com.interview.calendar.service.ReadInputCalendarService;

public class Application {
	public static void main(String[] args) {
		final ReadInputCalendarService readInputCalendarService = new ReadInputCalendarService();
		System.out.println("============First==========");
		System.out.println(new FindAvailableSlotService().findMeetableTimeSlots(readInputCalendarService.readInput(
				new String[][]{{"14:00", "16:00"}},
				new String[] {"9:00", "18:00"}),
				readInputCalendarService.readInput(new String[][]{}, new String[] {"10:00", "20:00"}),
				21));
		System.out.println("============Second==========");
		System.out.println(new FindAvailableSlotService().findMeetableTimeSlots(readInputCalendarService.readInput(
				new String[][]{{"10:00", "10:15"},{"12:00", "12:15"},{"14:00", "14:15"},{"16:00", "16:15"}},
				new String[] {"9:00", "18:00"}),
				readInputCalendarService.readInput(new String[][]{{"10:00", "10:55"},{"12:00", "12:55"},{"14:00", "14"
						+ ":55"},{"16:00", "16:55"}}, new String[] {"10:00", "20:00"}),
				31));
	}
}
