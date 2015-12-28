package org.capgemini.stock_exchange.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

	@Autowired
	StockRepository repository;// = new StockRepository();
	
//	@PostConstruct
//	public void init(){
//		if(repository.getData().isEmpty()){
//			repository.init();
//		}
//	}
	
	public List<Date> getFirstAndLastDays(){
		return repository.getFirstAndLastDays();
	}
}
