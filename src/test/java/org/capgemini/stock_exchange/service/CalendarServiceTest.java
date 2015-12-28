package org.capgemini.stock_exchange.service;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class CalendarServiceTest {
	
	@Autowired
	CalendarService service;// = new StockService();
	
	DateFormat format = new SimpleDateFormat("yyyyMMdd");

	@Test
	public void shouldReturnFirstAndLastDays() throws ParseException{
		List<Date> firstAndLastDays = service.getFirstAndLastDays();
		
		String stringFirstDate = "20011024";
		Date expectedFirstDay = format.parse(stringFirstDate);
		String stringLastDate = "20011026";
		Date expectedLastDay = format.parse(stringLastDate);
		
		assertEquals(expectedFirstDay, firstAndLastDays.get(0));
		assertEquals(expectedLastDay, firstAndLastDays.get(1));
	}
	
}
