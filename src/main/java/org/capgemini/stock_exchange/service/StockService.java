package org.capgemini.stock_exchange.service;

import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.mapper.StockMapper;
import org.capgemini.stock_exchange.repository.StockRepository;
import org.capgemini.stock_exchange.to.StockTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	private StockRepository repository;

	public List<StockTo> getStockByDate(Date date) {
		return StockMapper.mapEntityList(repository.getEntitiesByDate(date));
	}

	public List<StockTo> getStockByName(String name) {
		return StockMapper.mapEntityList(repository.getEntitiesByName(name));
	}

	public List<StockTo> getStock(String name, Date date) {
		return StockMapper.mapEntityList(repository.getEntities(name, date));
	}

}
