package org.capgemini.stock_exchange.utils;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final static int ROUND_UP_DECIMAL_PLACES = 2;
	private final static int MATH_RANDOM_MIN_VALUE_COUNTER_HELPER = 1;

	public double roundToMoneyFormat(double number) {

		double result = new BigDecimal(number).setScale(ROUND_UP_DECIMAL_PLACES, BigDecimal.ROUND_HALF_UP)
				.doubleValue();

		return result;
	}

	public int generateRandom(int min, int max) {

		int range = (max - min) + MATH_RANDOM_MIN_VALUE_COUNTER_HELPER;

		return (int) (Math.random() * range) + min;
	}

}
