package org.capgemini.stock_exchange.utils;

import org.capgemini.stock_exchange.to.StockPackTo;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	//TODO JBODZIOCH raz dziala a raz nie, wtf
	public double roundToMoneyFormat(double number) {
		
		number = Math.round(number * 100);
		number = number/100;
		
		return number;
	}
	
	public int generateRandom(int min, int max) {

		int range = (max - min) + 1;

		return (int) (Math.random() * range) + min;
	}

}
