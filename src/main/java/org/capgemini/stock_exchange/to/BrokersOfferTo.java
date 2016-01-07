package org.capgemini.stock_exchange.to;

public class BrokersOfferTo {

	private String stockName;
	private double cost;
	private int count;
	private double provision;
	
	public BrokersOfferTo(String stockName, double cost, int count, double provision) {
		this.stockName = stockName;
		this.cost = cost;
		this.count = count;
		this.provision = provision;
	}

	public double getProvision() {
		return provision;
	}

	public void setProvision(double provision) {
		this.provision = provision;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
