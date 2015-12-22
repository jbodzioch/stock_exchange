package org.capgemini.stock_exchange.broker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BrokerTest {
	
	Broker broker = new Broker();
	
	@Test
	public void shouldReturnPositiveValueForGivenFormula(){
		
		double expected = 1053;
		
		broker.buyStock(10, "INTEL", 20011024);
		
		assertEquals(expected, broker.getDailyOperationCost(), 0);
		
	}
	
	@Test
	public void shouldReturnNegativeValueForGivenFormula(){
		
		double expected = -1053;
		
		broker.sellStock(10, "INTEL", 20011024);
		
		assertEquals(expected, broker.getDailyOperationCost(), 0);
		
	}
	
	@Test
	public void shouldReturn0ForGivenFormula(){
		
		double expected = 0;
		
		broker.buyStock(10, "INTEL", 20011024);
		broker.sellStock(10, "INTEL", 20011024);
		
		assertEquals(expected, broker.getDailyOperationCost(), 0);
		
	}

}
