package org.capgemini.stock_exchange.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {

	private final String DATA_SPLITTER = ",";

	List<StockEntity> data = generateEntities(importData());

	public List<String> importData() {
		List<String> result = new ArrayList<String>();
		String adress = "C:\\Users\\JBODZIOC\\Desktop\\data.csv";

		File file = new File(adress);
		Scanner sc;

		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				result.add(sc.nextLine());
			}
			sc.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<StockEntity> generateEntities(List<String> inputData) {
		List<StockEntity> result = new ArrayList<StockEntity>();

		for (String line : inputData) {
			String splitLines[] = line.split(DATA_SPLITTER);
			result.add(new StockEntity(splitLines[0], Integer.parseInt(splitLines[1]), Double.parseDouble(splitLines[2])));
		}

		return result;
	}


	public List<StockEntity> getAllEntities() {

		return data;
	}

	public List<StockEntity> getEntitiesByDate(int date, List<StockEntity> inputList) {
		List<StockEntity> result = new ArrayList<StockEntity>();

		for (StockEntity entity : inputList) {
			if (entity.getDate() == date) {
				result.add(entity);
			}
		}

		return result;
	}

	public List<StockEntity> getEntitiesByName(String name, List<StockEntity> inputList) {
		List<StockEntity> result = new ArrayList<StockEntity>();

		for (StockEntity entity : inputList) {
			if (entity.getName().equals(name)) {
				result.add(entity);
			}
		}

		return result;
	}

	public List<StockEntity> sortEntitiesByDate(List<StockEntity> inputList) {

		Collections.sort(inputList);

		return inputList;
	}

}



