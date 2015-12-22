package org.capgemini.stock_exchange.entity;

public class StockEntity implements Comparable<StockEntity> {

	String name;
	int date;
	double cost;

	public StockEntity(String name, int date, double cost) {
		this.name = name;
		this.date = date;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int compareTo(StockEntity another) {
		if (this.getDate() < another.getDate()) {
			return -1;
		} else {
			return 1;
		}
	}

}
