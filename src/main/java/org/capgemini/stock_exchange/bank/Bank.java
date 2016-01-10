package org.capgemini.stock_exchange.bank;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.capgemini.stock_exchange.currency_exchange.CurrencyExchange;
import org.capgemini.stock_exchange.exception.TryingToSellUnavailableStockException;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("restriction")
@Component
public class Bank {

	private final static double INITIAL_MONEY_IN_PLN = 5000;
	private final static double INITIAL_MONEY_IN_EURO = 1250;

	private CurrencyExchange currencyExchange;
	private Utils utils;

	private double moneyInPLN;
	private double moneyInEURO;
	private List<StockPackTo> stock = new ArrayList<StockPackTo>();

	@Autowired
	public Bank(CurrencyExchange currencyExchange, Utils utils) {
		this.currencyExchange = currencyExchange;
		this.utils = utils;
	}

	@PostConstruct
	private void setInitialConditions() {
		this.moneyInPLN = utils.roundToMoneyFormat(INITIAL_MONEY_IN_PLN);
		this.moneyInEURO = utils.roundToMoneyFormat(INITIAL_MONEY_IN_EURO);
	}

	public void changeMoneyInPLN(double amount) {
		this.moneyInPLN = utils.roundToMoneyFormat(moneyInPLN + amount);
	}

	public void changeMoneyInEURO(double amount) {
		this.moneyInEURO = utils.roundToMoneyFormat(moneyInEURO + amount);
	}

	public void changePLNToEURO(double amount) {
		changeMoneyInPLN(-amount);
		changeMoneyInEURO(currencyExchange.exchangePLNToEURO(amount));
	}

	public void changeEUROToPLN(double amount) {
		changeMoneyInEURO(-amount);
		changeMoneyInPLN(currencyExchange.exchangeEUROToPLN(amount));
	}

	public void addStock(List<StockPackTo> stockPacks) {

		boolean checked = false;

		for (StockPackTo stockPack : stockPacks) {
			checked = false;
			for (StockPackTo stock : stock) {
				if (stockPack.getStockName().equals(stock.getStockName())) {
					stock.setCount(stock.getCount() + stockPack.getCount());
					checked = true;
				}
			}
			if (!checked) {
				stock.add(stockPack);
			}
		}
	}

	public void subtractStock(List<StockPackTo> stockPacks) throws TryingToSellUnavailableStockException {

		boolean checked = false;
		
		for (StockPackTo stockPack : stockPacks) {
			checked = false;
			for (StockPackTo stock : stock) {
				if (stockPack.getStockName().equals(stock.getStockName())) {
					if(stock.getCount() >= stockPack.getCount()){
						stock.setCount(stock.getCount() - stockPack.getCount());
						checked = true;
					}
					else{
						throw new TryingToSellUnavailableStockException("Trying to sell too much stock");
					}
				}
			}
			if(!checked){
				throw new TryingToSellUnavailableStockException("Trying to sell wrong stock");
			}
		}
	}

	public static double getInitialMoneyInPln() {
		return INITIAL_MONEY_IN_PLN;
	}

	public static double getInitialMoneyInEuro() {
		return INITIAL_MONEY_IN_EURO;
	}

	public double getMoneyInPLN() {
		return moneyInPLN;
	}

	public void setMoneyInPLN(double moneyInPLN) {
		this.moneyInPLN = moneyInPLN;
	}

	public void setMoneyInEURO(double moneyInEURO) {
		this.moneyInEURO = moneyInEURO;
	}

	public double getMoneyInEURO() {
		return moneyInEURO;
	}

	public List<StockPackTo> getStock() {
		return stock;
	}

	public void setStock(List<StockPackTo> stock) {
		this.stock = stock;
	}

}
