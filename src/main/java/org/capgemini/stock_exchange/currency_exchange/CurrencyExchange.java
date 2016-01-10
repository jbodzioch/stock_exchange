package org.capgemini.stock_exchange.currency_exchange;

import javax.annotation.PostConstruct;

import org.capgemini.stock_exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("restriction")
@Component
public class CurrencyExchange {

	private final static double TAX = 0.02;
	private final static int BASE_VALUE = 1;
	private final static int MIN_EXCHANGE_RATIO_TIMES_MUTLIPIER_TO_DECIMAL_PLACES = 3900;
	private final static int MAX_EXCHANGE_RATIO_TIMES_MUTLIPIER_TO_DECIMAL_PLACES = 4100;
	private final static int MULTIPIER_TO_3_DECIMAL_PLACES = 1000;

	private Utils utils;

	private double exchangeCourse;

	@Autowired
	public CurrencyExchange(Utils utils) {
		this.utils = utils;
	}

	@PostConstruct
	private void setExchangeCourse() {
		this.exchangeCourse = setRandomCourse();
	}

	public double getExchangeCourse() {
		return exchangeCourse;
	}

	public double exchangePLNToEURO(double amount) {
		return amount / (exchangeCourse * (BASE_VALUE + TAX));
	}

	public double exchangeEUROToPLN(double amount) {
		return amount * (exchangeCourse * (BASE_VALUE - TAX));
	}

	private double setRandomCourse() {

		int min = MIN_EXCHANGE_RATIO_TIMES_MUTLIPIER_TO_DECIMAL_PLACES;
		int max = MAX_EXCHANGE_RATIO_TIMES_MUTLIPIER_TO_DECIMAL_PLACES;

		double random = utils.generateRandom(min, max);

		return random / MULTIPIER_TO_3_DECIMAL_PLACES;
	}
}
