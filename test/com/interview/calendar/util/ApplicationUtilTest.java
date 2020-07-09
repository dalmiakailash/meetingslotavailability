package com.interview.calendar.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationUtilTest {

	@Test
	public void addMinutesInHour() {
		assertEquals(1150, ApplicationUtil.addMinutesInHour(1055, 55));
		assertEquals(1050, ApplicationUtil.addMinutesInHour(955, 55));
		assertEquals(1335, ApplicationUtil.addMinutesInHour(955, 220));
		assertEquals(1405, ApplicationUtil.addMinutesInHour(1025, 220));
	}
}
