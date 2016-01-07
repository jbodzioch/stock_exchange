package org.capgemini.stock_exchange.mapper;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.to.StockTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "MapperTest-context.xml")
public class MapperTest {

	@Value(value = "#{new java.text.SimpleDateFormat('${dateformat}')}")
	private DateFormat format;

	@Test
	public void shouldMapEntityToTo() throws ParseException {

		String expectedName = "name";
		String stringExpectedDate = "20151224";
		Date expectedDate = format.parse(stringExpectedDate);
		Double expectedCost = 14.5;

		StockEntity entity = new StockEntity(expectedName, expectedDate, expectedCost);

		StockTo to = StockMapper.map(entity);

		assertEquals(expectedName, to.getStockName());
		assertEquals(expectedDate, to.getDate());
		assertEquals(expectedCost, Double.valueOf(to.getCost()));
	}

	@Test
	public void shouldMapToToEntity() throws ParseException {

		String expectedName = "name";
		String stringExpectedDate = "20151224";
		Date expectedDate = format.parse(stringExpectedDate);
		double expectedCost = 14.5;

		StockTo to = new StockTo(expectedName, expectedDate, expectedCost);

		StockEntity entity = StockMapper.map(to);

		assertEquals(expectedName, entity.getStockName());
		assertEquals(expectedDate, entity.getDate());
		assertEquals(Double.valueOf(expectedCost), entity.getCost());
	}

}
