package org.capgemini.stock_exchange.broker;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BrokerTest-context.xml")
public class BrokerTest {
	@Autowired
	Broker broker;
	
	DateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	@Before
	public void init() throws ParseException{
		String stringFirstDate = "20011024";
		Date expectedFirstDay = format.parse(stringFirstDate);
	}
	@Test
	public void shouldReturnPositiveValueForGivenFormula() throws ParseException{
		String stringFirstDate = "20011024";
		broker.setDailyOperationCost(0);
		Date expectedFirstDay = format.parse(stringFirstDate);
		double expected = 1053;
		
		broker.buyStock(10, "INTEL", expectedFirstDay);
		
		assertEquals(expected, broker.getDailyOperationCost(), 0);
		
	}
	
	@Test
	public void shouldReturnNegativeValueForGivenFormula() throws ParseException{
		String stringFirstDate = "20011024";
		broker.setDailyOperationCost(0);
		Date expectedFirstDay = format.parse(stringFirstDate);
		double expected = -1053;
		
		broker.sellStock(10, "INTEL", expectedFirstDay);
		
		assertEquals(expected, broker.getDailyOperationCost(), 0);
		
	}
	
	@Test
	public void shouldReturn0ForGivenFormula() throws ParseException{
		
		broker.setDailyOperationCost(0);
		String stringFirstDate = "20011024";
		double expected = 0;
		Date expectedFirstDay = format.parse(stringFirstDate);
		broker.buyStock(10, "INTEL", expectedFirstDay);
		broker.sellStock(10, "INTEL", expectedFirstDay);
		
		assertEquals(expected, broker.getDailyOperationCost(), 0);
		
	}

}
