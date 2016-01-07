package org.capgemini.stock_exchange.mapper;

import java.util.ArrayList;
import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.to.StockTo;

public class StockMapper {

	public static StockTo map(StockEntity entity) {
		return new StockTo(entity.getStockName(), entity.getDate(), entity.getCost());
	}

	public static StockEntity map(StockTo to) {
		return new StockEntity(to.getStockName(), to.getDate(), to.getCost());
	}

	public static List<StockTo> mapEntityList(List<StockEntity> entities) {
		List<StockTo> result = new ArrayList<StockTo>();

		for (StockEntity entity : entities) {
			result.add(map(entity));
		}

		return result;
	}

	public static List<StockEntity> mapToList(List<StockTo> tos) {
		List<StockEntity> result = new ArrayList<StockEntity>();

		for (StockTo to : tos) {
			result.add(map(to));
		}

		return result;
	}

}
