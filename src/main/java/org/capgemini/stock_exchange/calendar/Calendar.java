package org.capgemini.stock_exchange.calendar;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.capgemini.stock_exchange.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("restriction")
@Component
public class Calendar {

	private final static int AMOUNT_OF_DAYS_TO_INCREMENT = 1;
	private final static int FIRST_DAY = 0;
	private final static int LAST_DAY = 1;

	private CalendarService service;

	private Date firstDay;
	private Date lastDay;
	private Date today;

	@Autowired
	public Calendar(CalendarService service) {
		this.service = service;
	}

	@PostConstruct
	public void setDays() {
		firstDay = service.getFirstAndLastDays().get(FIRST_DAY);
		lastDay = service.getFirstAndLastDays().get(LAST_DAY);
		today = firstDay;
	}

	public Date getToday() {
		return today;
	}

	public void nextDay() {
		today = DateUtils.addDays(today, AMOUNT_OF_DAYS_TO_INCREMENT);
	}

	public boolean isLastday() {
		return today.equals(lastDay);
	}

}
