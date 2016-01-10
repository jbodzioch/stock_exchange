package org.capgemini.stock_exchange.strategy.implementation;

import java.util.ArrayList;
import java.util.List;

import org.capgemini.stock_exchange.strategy.Strategy;
import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;
import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomStrategy implements Strategy {

	private final static int FIRST_STOCK = 0;
	private final static int LAST_STOCK_PROPER_POINTER = 1;
	private final static int MIN_STOCK_TO_TRADE = 0;
	private final static int MAX_STOCK_TO_TRADE = 10;

	private Utils utils;

	@Autowired
	public RandomStrategy(Utils utils) {
		this.utils = utils;
	}

	public List<StockPackTo> proposeStockToBuy(List<StockTo> todayStock) {
		List<StockPackTo> result = new ArrayList<StockPackTo>();

		if (!todayStock.isEmpty()) {
			int buy = utils.generateRandom(FIRST_STOCK, todayStock.size() - LAST_STOCK_PROPER_POINTER);
			int count = utils.generateRandom(MIN_STOCK_TO_TRADE, MAX_STOCK_TO_TRADE);

			result.add(new StockPackTo(todayStock.get(buy).getStockName(), count));
		}

		return result;
	}

	public List<StockPackTo> validateStockToBuy(List<BrokersOfferTo> brokersOffer) {
		List<StockPackTo> result = new ArrayList<StockPackTo>();

		for (BrokersOfferTo offer : brokersOffer) {
			result.add(new StockPackTo(offer.getStockName(), offer.getCount()));
		}

		return result;
	}

	public List<StockPackTo> proposeStockToSell(List<StockTo> todayStock, List<StockPackTo> availableStock) {
		List<StockPackTo> result = new ArrayList<StockPackTo>();

		if (!todayStock.isEmpty()&&!availableStock.isEmpty()) {
			int sell = utils.generateRandom(FIRST_STOCK, availableStock.size() - LAST_STOCK_PROPER_POINTER);
			int count = utils.generateRandom(MIN_STOCK_TO_TRADE, availableStock.get(sell).getCount());
			
			result.add(new StockPackTo(availableStock.get(sell).getStockName(), count));
		}

		return result;
	}

	public List<StockPackTo> validateStockToSell(List<BrokersOfferTo> brokersOffer) {
		List<StockPackTo> result = new ArrayList<StockPackTo>();

		for (BrokersOfferTo offer : brokersOffer) {
			result.add(new StockPackTo(offer.getStockName(), offer.getCount()));
		}

		return result;
	}

}
