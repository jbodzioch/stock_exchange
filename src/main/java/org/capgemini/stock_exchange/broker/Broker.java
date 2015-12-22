package org.capgemini.stock_exchange.broker;

import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.service.StockService;

public class Broker {
	
	StockService service = new StockService();
	
	double dailyOperationCost = 0;
	
	public void buyStock (int number, String name, int date){
		List<StockEntity> todayStock = service.getEntitiesByDate(date, service.getAllEntities());
		List<StockEntity> specificStock = service.getEntitiesByName(name, todayStock);
				
		dailyOperationCost += number*specificStock.get(0).getCost();
	}
	
	public void sellStock (int number, String name, int date){
		List<StockEntity> todayStock = service.getEntitiesByDate(date, service.getAllEntities());
		List<StockEntity> specificStock = service.getEntitiesByName(name, todayStock);
				
		dailyOperationCost -= number*specificStock.get(0).getCost();
	}

	public double getDailyOperationCost() {
		return dailyOperationCost;
	}

	public void setDailyOperationCost(double dailyOperationCost) {
		this.dailyOperationCost = dailyOperationCost;
	}

}
