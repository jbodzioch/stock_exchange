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
	private Long stockId;

	@Column(name = "stock_name", length = 50, nullable = false)
	private String stockName;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", columnDefinition = "DATE")
	private Date date;

	@Column(name = "cost", nullable = false)
	private Double cost;

	public StockEntity() {
	}

	public StockEntity(String stockName, Date date, Double cost) {
		this.stockName = stockName;
		this.date = date;
		this.cost = cost;
	}

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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
