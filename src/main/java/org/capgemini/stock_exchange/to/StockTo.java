package org.capgemini.stock_exchange.to;

import java.util.Date;

public class StockTo {

	private String stockName;
	private Date date;
	private double cost;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public StockTo(String stockName, Date date, double cost) {
		this.stockName = stockName;
		this.date = date;
		this.cost = cost;
	}

}
