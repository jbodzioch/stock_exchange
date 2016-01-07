package org.capgemini.stock_exchange.calendar;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.capgemini.stock_exchange.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.time.DateUtils;

@Component
public class Calendar {

	@Autowired
	CalendarService service;
	
	Date firstDay;
	Date lastDay;
	Date today;
	
	@PostConstruct
	public void setDays(){
		firstDay = service.getFirstAndLastDays().get(0);
		lastDay = service.getFirstAndLastDays().get(1);
		today = firstDay;
	}
	
	public Date getToday(){
		return today;
	}
	
	public void nextDay(){
		today = DateUtils.addDays(today, 1);
	}
	
	public boolean isLastday(){
		return today.equals(lastDay);
	}

	
}
