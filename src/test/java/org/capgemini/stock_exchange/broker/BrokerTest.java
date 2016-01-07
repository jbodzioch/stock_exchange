package org.capgemini.stock_exchange.broker;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.to.StockPackTo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BrokerTest-context.xml")
public class BrokerTest {
	
	@Autowired
	Broker broker;

	@Value(value = "#{new java.text.SimpleDateFormat('${dateformat}')}")
	private DateFormat format;
	
	List<StockPackTo> stockPacks = new ArrayList<StockPackTo>();
	
	@Before
	public void init() throws ParseException{
		
		if(stockPacks.isEmpty()){
			stockPacks.add(new StockPackTo("INTEL", 10));
		}
		
		String stringDate = "20011024";
		Date date = format.parse(stringDate);
		
		broker.updateTodayStock(date);
		broker.setTodayOperationResult(0);
	}
	
	@Test
	public void shouldReturnNegativeValueForGivenFormula() throws ParseException {

		double expected = -1053*1.05;
		double delta = Math.abs(expected - expected * 0.8);
		
		broker.checkBuy(stockPacks);
		broker.buyStock(stockPacks);

		assertEquals(expected, broker.getTodayOperationResult(), delta);

	}

	@Test
	public void shouldReturnPositiveValueForGivenFormula() throws ParseException {

		double expected = 1053*1.05;
		double delta = Math.abs(expected - expected*(1-0.02) * 0.8);

		broker.checkSell(stockPacks);
		broker.sellStock(stockPacks);

		assertEquals(expected, broker.getTodayOperationResult(), delta);

	}

	@Test
	public void shouldReturn0ForGivenFormula() throws ParseException {

		double expected = 0;
		double expectedForDelta = 1053*1.05;
		double delta = Math.abs((expectedForDelta - expectedForDelta*(1-0.02) * 0.8));
		
		broker.checkBuy(stockPacks);
		broker.checkSell(stockPacks);
		broker.buyStock(stockPacks);
		broker.sellStock(stockPacks);

		assertEquals(expected, broker.getTodayOperationResult(), delta);

	}

}
