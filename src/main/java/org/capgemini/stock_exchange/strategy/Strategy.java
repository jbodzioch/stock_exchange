package org.capgemini.stock_exchange.strategy;

import java.util.ArrayList;
import java.util.List;

import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;
import org.springframework.stereotype.Component;


public interface Strategy {

	public List<StockPackTo> proposeStockToBuy(List<StockTo> todayStock);
	
	public List<StockPackTo> validateStockToBuy(List<BrokersOfferTo> brokersOffer);
	
	public List<StockPackTo> proposeStockToSell(List<StockTo> todayStock);
	
	public List<StockPackTo> validateStockToSell(List<BrokersOfferTo> brokersOffer);
}
