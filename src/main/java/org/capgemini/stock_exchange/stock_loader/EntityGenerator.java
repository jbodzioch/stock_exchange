package org.capgemini.stock_exchange.stock_loader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityGenerator {

	private final String DATA_SPLITTER = ",";

	public StockEntity generateEntity(String inputData) {
		StockEntity result = null;

		String splitLines[] = inputData.split(DATA_SPLITTER);

		DateFormat format = new SimpleDateFormat("yyyyMMdd");

		Date date;
		try {
			date = format.parse(splitLines[1]);
			result = new StockEntity(splitLines[0], date, Float.parseFloat(splitLines[2]));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

}
