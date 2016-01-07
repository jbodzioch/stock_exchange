package org.capgemini.stock_exchange.currency_exchange;

import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class CurrencyExchange {
	
	@Autowired
	Utils utils;
 
	private final double TAX = 1.02;
	
	private double exchangeCourse;
	
	public double getExchangeCourse() {
		return exchangeCourse;
	}

	@PostConstruct
	public void setExchangeCourse(){
		this.exchangeCourse = setRandomCourse();
	}
	
	public double exchangePLNToEURO(double amount){
		return amount/(exchangeCourse*TAX);
	}
	
	public double exchangeEUROToPLN(double amount){
		return amount*(exchangeCourse/TAX);
	}
	
	private double setRandomCourse() {

		int min = 3900;
		int max = 4100;
		
		double random = utils.generateRandom(min, max);
		
		return random/1000;
	}
}
