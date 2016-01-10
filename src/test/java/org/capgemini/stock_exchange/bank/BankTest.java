package org.capgemini.stock_exchange.bank;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.capgemini.stock_exchange.exception.TryingToSellUnavailableStockException;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BankTest-context.xml")
public class BankTest {

	@Autowired
	private Bank bank;

	private List<StockPackTo> stockPacks = new ArrayList<StockPackTo>();
	private List<StockPackTo> stockPack = new ArrayList<StockPackTo>();

	@Before
	public void init() {

		bank.setMoneyInEURO(1250);
		bank.setMoneyInPLN(5000);

		if (bank.getStock().isEmpty()) {
			bank.getStock().add(new StockPackTo("INTEL", 10));
			bank.getStock().add(new StockPackTo("MICROSOFT", 10));
		}

		if (stockPacks.isEmpty()) {
			stockPacks.add(new StockPackTo("INTEL", 10));
			stockPacks.add(new StockPackTo("VALVE", 10));
		}
		if (stockPack.isEmpty()) {
			stockPack.add(new StockPackTo("INTEL", 10));
		}

		bank.getStock().get(0).setCount(10);
	}

	@Test
	public void shouldChangeAllPLNToEURO() {

		double expectedMoneyInPLN = 0;
		double expectedMoneyInEURO = 2500;
		double delta = 1250 * 0.15;

		bank.changePLNToEURO(5000);

		assertEquals(expectedMoneyInPLN, bank.getMoneyInPLN(), delta);
		assertEquals(expectedMoneyInEURO, bank.getMoneyInEURO(), delta);
	}

	@Test
	public void shouldChangeAllEUROToPLN() {

		double expectedMoneyInEURO = 0;
		double expectedMoneyInPLN = 10000;
		double delta = 5000 * 0.15;

		bank.changeEUROToPLN(1250);

		assertEquals(expectedMoneyInPLN, bank.getMoneyInPLN(), delta);
		assertEquals(expectedMoneyInEURO, bank.getMoneyInEURO(), delta);
	}

	@Test
	public void shouldAddStock() {

		bank.addStock(stockPacks);

		assertEquals(3, bank.getStock().size());
		assertEquals(20, bank.getStock().get(0).getCount());
		assertEquals(10, bank.getStock().get(1).getCount());
		assertEquals(10, bank.getStock().get(2).getCount());
	}

	@Test(expected=TryingToSellUnavailableStockException.class)
	public void shouldSubtractStockWithException() throws TryingToSellUnavailableStockException {

		bank.subtractStock(stockPacks);

		assertEquals(2, bank.getStock().size());
		assertEquals(0, bank.getStock().get(0).getCount());
		assertEquals(10, bank.getStock().get(1).getCount());
	}
	
	@Test
	public void shouldSubtractStock() throws TryingToSellUnavailableStockException {

		bank.subtractStock(stockPack);

		assertEquals(2, bank.getStock().size());
		assertEquals(0, bank.getStock().get(0).getCount());
		assertEquals(10, bank.getStock().get(1).getCount());
	}

}
