package org.capgemini.stock_exchange.stock_loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StockLoader {

	@Autowired
	EntityGenerator entityGenerator;

	@Value(value = "#{applicationProperties['adress']}")
	String adress;

	public void importData() {

		File file = new File(adress);
		Scanner sc;

		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				saveToDatabase(sc.nextLine());
			}
			sc.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void saveToDatabase(String inputData) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		StockEntity aE = entityGenerator.generateEntity(inputData);
		entityManager.clear();

		entityManager.merge(aE);
		entityManager.flush();
		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();

	}

}
