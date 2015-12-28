package org.capgemini.stock_exchange.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.stock_loader.EntityGenerator;
import org.capgemini.stock_exchange.stock_loader.StockLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Repository
public class StockRepository {

	@Autowired
	StockLoader stockLoader;

	public List<StockEntity> getEntitiesByDate(Date date) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<StockEntity> resultList = entityManager.createQuery("from StockEntity ee where ee.date = :last")
				.setParameter("last", date).getResultList();

		entityManager.close();
		entityManagerFactory.close();

		return resultList;
	}

	public List<StockEntity> getEntitiesByName(String stock_name) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<StockEntity> resultList = entityManager.createQuery("from StockEntity ee where ee.stockName = :first")
				.setParameter("first", stock_name).getResultList();

		entityManager.close();
		entityManagerFactory.close();

		return resultList;
	}

	public List<StockEntity> getEntities(String stock_name, Date date) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<StockEntity> resultList = entityManager
				.createQuery("from StockEntity ee where ee.stockName = :first and ee.date = :last")
				.setParameter("first", stock_name).setParameter("last", date).getResultList();

		entityManager.close();
		entityManagerFactory.close();

		return resultList;
	}

	public List<Date> getFirstAndLastDays() {
		List<Date> result = new ArrayList<Date>();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<Date> resultListMin = entityManager.createQuery("select min(ee.date)from StockEntity ee")
				.getResultList();

		List<Date> resultListMax = entityManager.createQuery("select max(ee.date)from StockEntity ee")
				.getResultList();

		entityManager.close();
		entityManagerFactory.close();

		result.add(resultListMin.get(0));
		result.add(resultListMax.get(0));

		return result;
	}

}
