package org.capgemini.stock_exchange.stock_loader;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EntityGenerator {

	private final static String DATA_SPLITTER = ",";
	private final int NAME = 0;
	private final int DATE = 1;
	private final int COST = 2;

	@Value(value = "#{new java.text.SimpleDateFormat('${dateformat}')}")
	private DateFormat format;

	public StockEntity generateEntity(String inputData) {

		StockEntity result = null;
		String splitLines[] = inputData.split(DATA_SPLITTER);
		Date date;

		try {
			date = format.parse(splitLines[DATE]);
			result = new StockEntity(splitLines[NAME], date, Double.parseDouble(splitLines[COST]));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

}
