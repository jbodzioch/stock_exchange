package org.capgemini.stock_exchange.service;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class CalendarServiceTest {

	@Autowired
	private CalendarService service;

	@Value(value = "#{new java.text.SimpleDateFormat('${dateformat}')}")
	private DateFormat format;

	@Test
	public void shouldReturnFirstAndLastDays() throws ParseException {

		List<Date> firstAndLastDays = service.getFirstAndLastDays();

		String stringFirstDate = "20011024";
		Date expectedFirstDay = format.parse(stringFirstDate);
		String stringLastDate = "20011026";
		Date expectedLastDay = format.parse(stringLastDate);

		assertEquals(expectedFirstDay, firstAndLastDays.get(0));
		assertEquals(expectedLastDay, firstAndLastDays.get(1));
	}

}
