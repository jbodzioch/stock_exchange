package org.capgemini.stock_exchange.broker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.service.StockService;
import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;
import org.capgemini.stock_exchange.utils.Utils;
import org.h2.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.math.*;

@Component
public class Broker {

	@Autowired
	private StockService service;
	
	@Autowired
	private Utils utils;

	private double todayOperationResult = 0;
	private List<StockTo> todayStock = new ArrayList<StockTo>();
	private List<BrokersOfferTo> todayBuyOffer = new ArrayList<BrokersOfferTo>();
	private List<BrokersOfferTo> todaySellOffer = new ArrayList<BrokersOfferTo>();

	public double getTodayOperationResult() {
		return todayOperationResult;
	}

	public void setTodayOperationResult(double todayOperationResult) {
		this.todayOperationResult = todayOperationResult;
	}

	public List<StockTo> getTodayStock() {
		return todayStock;
	}

	public void updateTodayStock(Date date) {
		todayStock = service.getStockByDate(date);
	}

	public List<BrokersOfferTo> checkBuy(List<StockPackTo> stockPacks) {
		List<BrokersOfferTo> result = new ArrayList<BrokersOfferTo>();

		for (StockPackTo stockPack : stockPacks) {
			result.add(new BrokersOfferTo(stockPack.getStockName(), checkActualBuyCost(stockPack),
					checkAvailability(stockPack), checkProvision(stockPack)));
		}

		todayBuyOffer = result;
		return result;
	}

	public List<BrokersOfferTo> checkSell(List<StockPackTo> stockPacks) {
		List<BrokersOfferTo> result = new ArrayList<BrokersOfferTo>();

		for (StockPackTo stockPack : stockPacks) {
			result.add(new BrokersOfferTo(stockPack.getStockName(), checkActualSellCost(stockPack),
					checkAvailability(stockPack), checkProvision(stockPack)));
		}

		todaySellOffer = result;
		return result;
	}

	private double checkTax() {

		int min = 0;
		int max = 2;

		return 0.01 * utils.generateRandom(min, max);
	}

	private double checkActualBuyCost(StockPackTo stockPack) {

		return utils.roundToMoneyFormat(checkTodayStockCost(stockPack) * (1 + checkTax()));
	}

	private double checkActualSellCost(StockPackTo stockPack) {

		return utils.roundToMoneyFormat(checkTodayStockCost(stockPack) * (1 - checkTax()));
	}

	private int checkAvailability(StockPackTo stockPack) {

		int min = (int) (stockPack.getCount() * 0.8);
		int max = stockPack.getCount();

		return utils.generateRandom(min, max);
	}

	public void buyStock(List<StockPackTo> stockPacks) {

		for (StockPackTo stockPackTo : stockPacks) {
			BrokersOfferTo temp = checkActualStockBuy(stockPackTo);
			todayOperationResult -= (utils.roundToMoneyFormat(temp.getCost() * temp.getCount()) + temp.getProvision());
		}
	}

	public void sellStock(List<StockPackTo> stockPacks) {

		for (StockPackTo stockPackTo : stockPacks) {
			BrokersOfferTo temp = checkActualStockSell(stockPackTo);
			todayOperationResult += (utils.roundToMoneyFormat(temp.getCost() * temp.getCount()) + temp.getProvision());
		}
	}

	private double checkProvision(StockPackTo stockPack) {
		double provision = (checkTodayStockCost(stockPack) * stockPack.getCount()) * 0.05;

		if ((checkTodayStockCost(stockPack) * stockPack.getCount()) * 0.05 < 5) {
			provision = 5;
		}

		return utils.roundToMoneyFormat(provision);
	}

	private double checkTodayStockCost(StockPackTo stockPack) {

		double result = 0;

		for (StockTo to : todayStock) {
			if (to.getStockName().equals(stockPack.getStockName())) {
				result = to.getCost();
			}
		}

		return result;
	}

	private BrokersOfferTo checkActualStockBuy(StockPackTo stockPack) {

		BrokersOfferTo result = null;

		for (BrokersOfferTo to : todayBuyOffer) {
			if (to.getStockName().equals(stockPack.getStockName())) {
				result = to;
			}
		}

		return result;
	}

	private BrokersOfferTo checkActualStockSell(StockPackTo stockPack) {

		BrokersOfferTo result = null;

		for (BrokersOfferTo to : todaySellOffer) {
			if (to.getStockName().equals(stockPack.getStockName())) {
				result = to;
			}
		}

		return result;
	}

}
