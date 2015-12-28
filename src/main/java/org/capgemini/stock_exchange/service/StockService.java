package org.capgemini.stock_exchange.service;

import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	@Autowired
	StockRepository repository;// = new StockRepository();
	
//	@PostConstruct
//	public void init(){
//		if(repository.getData().isEmpty()){
//			repository.init();
//		}
//	}


	public List<StockEntity> getEntitiesByDate(Date date){
		return repository.getEntitiesByDate(date);
	}
	
	public List<StockEntity> getEntitiesByName(String name){
		return repository.getEntitiesByName(name);
	}
	
	public List<StockEntity> getEntities(String name, Date date){
		return repository.getEntities(name, date);
	}
	
}
