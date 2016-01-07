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
public class RandomStrategy implements Strategy{
	

	//TODO JBODZIOCH jak to zrobic autowired bo cos nie dziala
	Utils utils = new Utils();
	
	public List<StockPackTo> proposeStockToBuy(List<StockTo> todayStock){
		List<StockPackTo> result = new ArrayList<StockPackTo>();
		
		int buy = utils.generateRandom(0, todayStock.size()-1);
		int count = utils.generateRandom(1, 10);
		
		result.add(new StockPackTo(todayStock.get(buy).getStockName(), count));
		
		return result;
	}
	
	public List<StockPackTo> validateStockToBuy(List<BrokersOfferTo> brokersOffer){
		List<StockPackTo> result = new ArrayList<StockPackTo>();
		
		for(BrokersOfferTo offer: brokersOffer){
			result.add(new StockPackTo(offer.getStockName(), offer.getCount()));
		}
		
		return result;
	}
	
	public List<StockPackTo> proposeStockToSell(List<StockTo> todayStock){
		List<StockPackTo> result = new ArrayList<StockPackTo>();
		
		int sell = utils.generateRandom(0, todayStock.size()-1);
		int count = utils.generateRandom(1, 10);
		
		result.add(new StockPackTo(todayStock.get(sell).getStockName(), count));

		return result;
	}
	
	public List<StockPackTo> validateStockToSell(List<BrokersOfferTo> brokersOffer){
		List<StockPackTo> result = new ArrayList<StockPackTo>();
		
		for(BrokersOfferTo offer: brokersOffer){
			result.add(new StockPackTo(offer.getStockName(), offer.getCount()));
		}
		
		return result;
	}

}
