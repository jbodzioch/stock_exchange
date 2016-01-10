package org.capgemini.stock_exchange.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.to.StockTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class StockServiceTest {

	@Autowired
	private StockService service;

	@Value(value = "#{new java.text.SimpleDateFormat('${dateformat}')}")
	private DateFormat format;

	@Test
	public void shouldGetDataByNameAndDate() throws ParseException {

		String name = "INTEL";
		String stringDate = "20011024";
		Date date = format.parse(stringDate);
		int expectedSize = 1;

		List<StockTo> stockFound = service.getStock(name, date);

		assertFalse(stockFound.isEmpty());
		assertEquals(expectedSize, stockFound.size());
	}

	@Test
	public void shouldGetDataByDate() throws ParseException {

		String stringDate = "20011024";
		Date date = format.parse(stringDate);
		int expectedSize = 2;

		List<StockTo> stockFound = service.getStockByDate(date);

		assertFalse(stockFound.isEmpty());
		assertEquals(expectedSize, stockFound.size());
	}

	@Test
	public void shouldGetDataByName() {

		String name = "INTEL";
		int expectedSize = 3;

		List<StockTo> stockFound = service.getStockByName(name);

		assertFalse(stockFound.isEmpty());
		assertEquals(expectedSize, stockFound.size());
	}

}
