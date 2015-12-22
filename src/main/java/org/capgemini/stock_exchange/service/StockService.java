package org.capgemini.stock_exchange.service;

import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	
	StockRepository repository = new StockRepository();
	
	public List<StockEntity> getAllEntities(){
		return repository.getAllEntities();
	}

	public List<StockEntity> getEntitiesByDate(int date, List<StockEntity> entities){
		return repository.getEntitiesByDate(date, entities);
	}
	
	public List<StockEntity> getEntitiesByName(String name, List<StockEntity> entities){
		return repository.getEntitiesByName(name, entities);
	}
	
	public List<StockEntity> sortEntitiesByDate(List<StockEntity> entities){
		return repository.sortEntitiesByDate(entities);
	}
	
}
