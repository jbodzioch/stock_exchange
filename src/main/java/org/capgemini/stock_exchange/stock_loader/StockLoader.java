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

	private EntityGenerator entityGenerator;

	@Value(value = "#{applicationProperties['adress']}")
	private String adress;

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	@Autowired
	public StockLoader(EntityGenerator entityGenerator) {
		this.entityGenerator = entityGenerator;
	}

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

		startTransaction();

		StockEntity entity = entityGenerator.generateEntity(inputData);
		entityManager.clear();
		entityManager.merge(entity);

		endTransaction();
	}

	private void startTransaction() {

		entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		entityManager = entityManagerFactory.createEntityManager();

		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	private void endTransaction() {

		entityManager.flush();
		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();
	}

}
