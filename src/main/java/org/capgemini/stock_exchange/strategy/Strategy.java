package org.capgemini.stock_exchange.strategy;

import java.util.List;

import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;

public interface Strategy {

	public List<StockPackTo> proposeStockToBuy(List<StockTo> todayStock);

	public List<StockPackTo> validateStockToBuy(List<BrokersOfferTo> brokersOffer);

	public List<StockPackTo> proposeStockToSell(List<StockTo> todayStock, List<StockPackTo> availableStock);

	public List<StockPackTo> validateStockToSell(List<BrokersOfferTo> brokersOffer);
}
