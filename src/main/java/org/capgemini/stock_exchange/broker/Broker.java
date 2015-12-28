package org.capgemini.stock_exchange.broker;

import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Broker {
	@Autowired
	StockService service;// = new StockService();
	
	double dailyOperationCost = 0;
	
	public void buyStock (int number, String name, Date date){
		List<StockEntity> todayStock = service.getEntities(name, date);
				
		dailyOperationCost += number*todayStock.get(0).getCost();
	}
	
	public void sellStock (int number, String name, Date date){
		List<StockEntity> todayStock = service.getEntities(name, date);
				
		dailyOperationCost -= number*todayStock.get(0).getCost();
	}

	public double getDailyOperationCost() {
		return dailyOperationCost;
	}

	public void setDailyOperationCost(double dailyOperationCost) {
		this.dailyOperationCost = dailyOperationCost;
	}

}
