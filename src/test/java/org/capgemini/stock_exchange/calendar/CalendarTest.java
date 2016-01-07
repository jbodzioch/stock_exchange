package org.capgemini.stock_exchange.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CalendarTest-context.xml")
public class CalendarTest {
	
	@Autowired
	Calendar calendar;
	
	@Value(value = "#{new java.text.SimpleDateFormat('${dateformat}')}")
	private DateFormat format;
	
	@Test
	public void shouldReturnFirstDay() throws ParseException{
		
		String stringDate = "20011024";
		Date actualDate = format.parse(stringDate);
		
		calendar.setDays();
		
		assertFalse(calendar.isLastday());
		assertEquals(calendar.getToday(), actualDate);	
	}
	
	@Test
	public void shouldReturnNextDay() throws ParseException{
		
		String stringDate = "20011025";
		Date actualDate = format.parse(stringDate);
		
		calendar.setDays();
		calendar.nextDay();
		
		assertFalse(calendar.isLastday());
		assertEquals(calendar.getToday(), actualDate);
	}
	
	@Test
	public void shouldReturnLastDay() throws ParseException{
		
		String stringDate = "20011026";
		Date actualDate = format.parse(stringDate);
		
		calendar.setDays();
		calendar.nextDay();
		calendar.nextDay();
		
		assertTrue(calendar.isLastday());
		assertEquals(calendar.getToday(), actualDate);
	}
}
