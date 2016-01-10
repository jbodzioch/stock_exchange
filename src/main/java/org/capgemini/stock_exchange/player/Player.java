package org.capgemini.stock_exchange.player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.capgemini.stock_exchange.bank.Bank;
import org.capgemini.stock_exchange.broker.Broker;
import org.capgemini.stock_exchange.calendar.Calendar;
import org.capgemini.stock_exchange.exception.TryingToSellUnavailableStockException;
import org.capgemini.stock_exchange.strategy.Strategy;
import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;
import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Player {

	private static final Logger log = Logger.getLogger(Player.class.getName());
	private static final double AMOUNT_OF_PLN_TRADED_FOR_EURO = 0.5;
	private static final double INITIAL_STOCK_VALUE = 0;
	private static final int MAX_APPROPRIATE_LEFT_STOCK_COUNT = 0;

	private Calendar calendar;
	private Broker broker;
	private Bank bank;
	private Strategy randomStrategy;
	private Utils utils;

	@Autowired
	public Player(Calendar calendar, Broker broker, Bank bank, Strategy anotherRandomStrategy, Utils utils) {
		this.calendar = calendar;
		this.broker = broker;
		this.bank = bank;
		this.randomStrategy = anotherRandomStrategy;
		this.utils = utils;
	}

	private List<StockTo> list = new ArrayList<StockTo>();

	public void play() throws TryingToSellUnavailableStockException {

		while (!calendar.isLastday()) {

			bank.changeEUROToPLN(bank.getMoneyInEURO());
			broker.updateTodayStock(calendar.getToday());

			list = broker.getTodayStock();

			buyStockAtCurrentDay();
			sellStockAtCurrentDay();
			storeMoneyInBankAndExchange();

			calendar.nextDay();
		}

		bank.changeEUROToPLN(bank.getMoneyInEURO());
		broker.updateTodayStock(calendar.getToday());
		list = broker.getTodayStock();
		sellStockAtCurrentDay();
		storeMoneyInBankAndExchange();

		sumUp();
	}

	private void buyStockAtCurrentDay() {
		
		List<StockPackTo> tempStockPacksToBuy;
		List<BrokersOfferTo> tempBrokersOffer;

		tempStockPacksToBuy = randomStrategy.proposeStockToBuy(list);
		tempBrokersOffer = broker.checkBuy(tempStockPacksToBuy);
		tempStockPacksToBuy = randomStrategy.validateStockToBuy(tempBrokersOffer);
		broker.buyStock(tempStockPacksToBuy);
		bank.addStock(tempStockPacksToBuy);
	}

	private void sellStockAtCurrentDay() throws TryingToSellUnavailableStockException {
		
		List<StockPackTo> tempStockPacksToSell;
		List<BrokersOfferTo> tempBrokersOffer;

		tempStockPacksToSell = randomStrategy.proposeStockToSell(list, bank.getStock());
		tempBrokersOffer = broker.checkSell(tempStockPacksToSell);
		tempStockPacksToSell = randomStrategy.validateStockToSell(tempBrokersOffer);
		broker.sellStock(tempStockPacksToSell);
		bank.subtractStock(tempStockPacksToSell);
	}

	private void storeMoneyInBankAndExchange() {

		double tempCash;
		
		tempCash = broker.getTodayOperationResult();
		bank.changeMoneyInPLN(tempCash);
		bank.changePLNToEURO(bank.getMoneyInPLN() * AMOUNT_OF_PLN_TRADED_FOR_EURO);
	}

	private void sumUp() {
		
		log.info("Player has started with: " + String.valueOf(Bank.getInitialMoneyInPln()) + " PLN and "
				+ String.valueOf(Bank.getInitialMoneyInEuro()) + " EURO");
		log.info("Player has finished with: " + String.valueOf(bank.getMoneyInPLN()) + " PLN and "
				+ String.valueOf(bank.getMoneyInEURO()) + " EURO");
		log.info("Stock unsold at last day: \n" + getListOfUnsoldStock());
		log.info("Raw unsold stock value: " + String.valueOf(estimateRawStockValue())+ " PLN");
	}
	
	private String getListOfUnsoldStock(){
		
		String result ="";
		
		for (StockPackTo stockPack : bank.getStock()) {
			if (stockPack.getCount() > MAX_APPROPRIATE_LEFT_STOCK_COUNT) {
				result += (String.valueOf(stockPack.getCount()) + " stock of " + stockPack.getStockName() + "\n");
			}
		}
		
		return result;
	}
	
	private double estimateRawStockValue(){

		double result = INITIAL_STOCK_VALUE;
		
		for (StockPackTo stockPack : bank.getStock()) {
			for(StockTo to : list){
				if(stockPack.getStockName().equals(to.getStockName())){
					result += to.getCost() * stockPack.getCount();
				}
			}
		}
		
		return utils.roundToMoneyFormat(result);
	}
}
