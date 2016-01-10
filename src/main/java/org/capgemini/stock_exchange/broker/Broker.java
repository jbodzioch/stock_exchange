package org.capgemini.stock_exchange.broker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.capgemini.stock_exchange.service.StockService;
import org.capgemini.stock_exchange.to.BrokersOfferTo;
import org.capgemini.stock_exchange.to.StockPackTo;
import org.capgemini.stock_exchange.to.StockTo;
import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("restriction")
@Component
public class Broker {

	private final static double RESET_OPERATION_RESULT = 0;
	private final static double PERCENTAGE_MULTIPLIER = 0.01;
	private final static double PROVISION_MULTIPLIER = 0.05;
	private final static double MIN_AVAILABLE_STOCK_MULTIPLIER = 0.8;
	private final static int MIN_TAX_IN_PERCENT = 0;
	private final static int MAX_TAX_IN_PERCENT = 2;
	private final static int BASE_VALUE = 1;
	private final static int MIN_PROVISION = 5;

	private StockService service;
	private Utils utils;

	private double todayOperationResult;
	private List<StockTo> todayStock = new ArrayList<StockTo>();
	private List<BrokersOfferTo> todayBuyOffer = new ArrayList<BrokersOfferTo>();
	private List<BrokersOfferTo> todaySellOffer = new ArrayList<BrokersOfferTo>();

	@Autowired
	public Broker(StockService service, Utils utils) {
		this.service = service;
		this.utils = utils;
	}

	@PostConstruct
	public void setInitialConditions() {
		this.todayOperationResult = utils.roundToMoneyFormat(RESET_OPERATION_RESULT);
	}

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
		this.todayOperationResult = utils.roundToMoneyFormat(RESET_OPERATION_RESULT);
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

		int min = MIN_TAX_IN_PERCENT;
		int max = MAX_TAX_IN_PERCENT;

		return PERCENTAGE_MULTIPLIER * utils.generateRandom(min, max);
	}

	private double checkActualBuyCost(StockPackTo stockPack) {

		return utils.roundToMoneyFormat(checkTodayStockCost(stockPack) * (BASE_VALUE + checkTax()));
	}

	private double checkActualSellCost(StockPackTo stockPack) {

		return utils.roundToMoneyFormat(checkTodayStockCost(stockPack) * (BASE_VALUE - checkTax()));
	}

	private int checkAvailability(StockPackTo stockPack) {

		int min = (int) (stockPack.getCount() * MIN_AVAILABLE_STOCK_MULTIPLIER);
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
		double provision = (checkTodayStockCost(stockPack) * stockPack.getCount()) * PROVISION_MULTIPLIER;

		if ((checkTodayStockCost(stockPack) * stockPack.getCount()) * PROVISION_MULTIPLIER < MIN_PROVISION) {
			provision = MIN_PROVISION;
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
