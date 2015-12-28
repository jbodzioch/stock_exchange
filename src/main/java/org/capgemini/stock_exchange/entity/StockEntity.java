package org.capgemini.stock_exchange.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stock")
public class StockEntity {

	@Id
	@Column(name = "stock_id", length = 11, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long stockId;

	@Column(name = "stock_name", length = 50, nullable = false)
	String stockName;
	
	public StockEntity(){}

	public StockEntity(String stockName, Date date, Float cost) {
		this.stockName = stockName;
		this.date = date;
		this.cost = cost;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", columnDefinition = "DATE")
	Date date;

	@Column(name = "cost", nullable = false)
	Float cost;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

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

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

}
