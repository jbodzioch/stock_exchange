package org.capgemini.stock_exchange.player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.capgemini.stock_exchange.bank.Bank;
import org.capgemini.stock_exchange.broker.Broker;
import org.capgemini.stock_exchange.calendar.Calendar;
import org.capgemini.stock_exchange.strategy.Strategy;
import org.capgemini.stock_exchange.strategy.implementation.RandomStrategy;
import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
//@ContextConfiguration(locations = "spring-context.xml")
public class Player {

	private static final Logger log = Logger.getLogger(Player.class.getName());

	@Autowired
	Calendar calendar;

	@Autowired
	Broker broker;
	
	@Autowired
	Bank bank;
	
	//TODO JBODZIOCH zapytac o implementacje w springu
	Strategy strategy = new RandomStrategy();

	List<StockTo> list = new ArrayList<StockTo>();

	List<StockPackTo> stockPacks = new ArrayList<StockPackTo>();

	List<BrokersOfferTo> offer = new ArrayList<BrokersOfferTo>();
	
	double cash = 0;

	public void play() {


		while (!calendar.isLastday()) {

			broker.updateTodayStock(calendar.getToday());
			list = broker.getTodayStock();
			
			stockPacks = strategy.proposeStockToBuy(list);
			
			offer = broker.checkBuy(stockPacks);
			
			stockPacks = strategy.validateStockToBuy(offer);
			
			broker.buyStock(stockPacks);
			bank.addStock(stockPacks);

			stockPacks = strategy.proposeStockToSell(list);
			
			offer = broker.checkSell(stockPacks);
			
			stockPacks = strategy.validateStockToSell(offer);
			
			broker.sellStock(stockPacks);
			bank.subtractStock(stockPacks);

			cash = broker.getTodayOperationResult();
			bank.changeMoneyInPLN(cash);
			
			bank.changePLNToEURO(bank.getMoneyInPLN()/2);

			calendar.nextDay();
		}

		offer = broker.checkSell(bank.getStock());
		stockPacks = strategy.validateStockToSell(offer);
		broker.sellStock(stockPacks);
		
		bank.changeEUROToPLN(bank.getMoneyInEURO());

		sum();
	}

	private void sum() {
		log.info("Strategy has generated: " + (bank.getMoneyInPLN()-10000) +" PLN");
	}
}
