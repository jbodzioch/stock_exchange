package org.capgemini.stock_exchange.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.capgemini.stock_exchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

	@Autowired
	private StockRepository repository;

	//TODO JBODZIOCH spytaj o adnotacje
//	@SuppressWarnings("restriction")
//	@PostConstruct
//	public void init() {
//		repository.init();
//		repository.checkDatabase();
//	}

	@SuppressWarnings("restriction")
	@PreDestroy
	public void close() {
		repository.close();
	}

	public List<Date> getFirstAndLastDays() {
		return repository.getFirstAndLastDays();
	}
}
