package org.capgemini.stock_exchange.to;

public class StockPackTo {

	private String stockName;
	private int count;

	public StockPackTo(String stockName, int count) {
		this.stockName = stockName;
		this.count = count;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
