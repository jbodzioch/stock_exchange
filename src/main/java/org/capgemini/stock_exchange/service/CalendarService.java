package org.capgemini.stock_exchange.service;

import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

	private StockRepository repository;

	@Autowired
	public CalendarService(StockRepository repository) {
		this.repository = repository;
	}

	public List<Date> getFirstAndLastDays() {
		return repository.getFirstAndLastDays();
	}
}
