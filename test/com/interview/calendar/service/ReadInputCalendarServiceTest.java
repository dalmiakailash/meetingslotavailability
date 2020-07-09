package com.interview.calendar.service;

import com.interview.calendar.model.MeetingsCalendar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadInputCalendarServiceTest {

	@Test
	public void shouldBeAbleInputDataAsCalendar_1(){
		final String[][] timeSlots = {{"9:00","10:30"},{
				"12:00","13:00"
		},{"16:00","18:00"}};
		final MeetingsCalendar actual = new ReadInputCalendarService().readInput(timeSlots, new String[]{"9:00",
				"20:00"});
		assertEquals(3, actual.getFreeTimeSlots().size());
	}

	@Test
	public void shouldBeAbleInputDataAsCalendar_2(){
		final String[][] timeSlots = {{"9:00","10:30"},{
				"12:00","13:00"
		},{"16:00","18:00"}, {"18:00","20:00"}};
		final MeetingsCalendar actual = new ReadInputCalendarService().readInput(timeSlots, new String[]{"9:00",
				"20:00"});
		assertEquals(2, actual.getFreeTimeSlots().size());
	}


	@Test
	public void shouldBeAbleInputDataAsCalendar_3(){
		final String[][] timeSlots = {{"10:00","10:30"},{
				"12:00","13:00"
		},{"16:00","18:00"}};
		final MeetingsCalendar actual = new ReadInputCalendarService().readInput(timeSlots, new String[]{"9:00",
				"20:00"});
		assertEquals(4, actual.getFreeTimeSlots().size());
	}
}
