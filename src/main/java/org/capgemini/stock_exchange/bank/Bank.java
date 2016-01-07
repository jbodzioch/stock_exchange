package org.capgemini.stock_exchange.bank;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.capgemini.stock_exchange.currency_exchange.CurrencyExchange;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bank {
	
	@Autowired
	CurrencyExchange currencyExchange;
	
	@Autowired
	Utils utils;
	
	private double moneyInPLN;
	private double moneyInEURO;
	private List<StockPackTo> stock;
	
	@PostConstruct
	public void setInitialConditions(){
		this.moneyInPLN = 5000;
		this.moneyInEURO = 1250;
		this.stock = new ArrayList<StockPackTo>();
	}
	
	public void changeMoneyInPLN(double amount){
		this.moneyInPLN = moneyInPLN + amount;
	}
	
	public void changeMoneyInEURO(double amount){
		this.moneyInEURO = moneyInEURO + amount;
	}
	
	public void changePLNToEURO(double amount){
		changeMoneyInPLN(utils.roundToMoneyFormat(-amount));
		changeMoneyInEURO(utils.roundToMoneyFormat(currencyExchange.exchangePLNToEURO(amount)));
	}
	
	public void changeEUROToPLN(double amount){
		changeMoneyInEURO(utils.roundToMoneyFormat(-amount));
		changeMoneyInPLN(utils.roundToMoneyFormat(currencyExchange.exchangeEUROToPLN(amount)));
	}
	
	public void addStock(List<StockPackTo> stockPacks){
		
		boolean checked = false;
		
		for(StockPackTo stockPack : stockPacks){
			checked = false;
			for(StockPackTo stock : stock){
				if(stockPack.getStockName().equals(stock.getStockName())){
					stock.setCount(stock.getCount()+stockPack.getCount());
					checked = true;
				}
			}
			if(!checked){
				stock.add(stockPack);
			}
		}
	}
	
	public void subtractStock(List<StockPackTo> stockPacks){

		for(StockPackTo stockPack : stockPacks){
			for(StockPackTo stock : stock){
				if(stockPack.getStockName().equals(stock.getStockName())){
					stock.setCount(stock.getCount()-stockPack.getCount());

				}
			}

		}
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
