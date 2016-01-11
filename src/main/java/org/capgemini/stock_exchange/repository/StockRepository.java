package org.capgemini.stock_exchange.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.stock_loader.StockLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@SuppressWarnings({ "restriction", "unchecked" })
@Repository
public class StockRepository {

	private final static Long FIRST_KEY = 1L;
	private final static int FIRST_VALUE = 0;
	
	@Value(value = "#{applicationProperties['entityManagerFactoryName']}")
	private String entityManagerFactoryName;

	private StockLoader stockLoader;

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Autowired
	public StockRepository(StockLoader stockLoader) {
		this.stockLoader = stockLoader;
	}

	@PostConstruct
	public void checkDatabase() {

		if (getEmployee() == null) {
			stockLoader.importData();
		}
	}

	public List<StockEntity> getEntitiesByDate(Date date) {

		init();

		List<StockEntity> resultList = entityManager.createQuery("from StockEntity ee where ee.date = :date")
				.setParameter("date", date).getResultList();

		close();

		return resultList;
	}

	private void init() {

		entityManagerFactory = Persistence.createEntityManagerFactory(entityManagerFactoryName);
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<StockEntity> getEntitiesByName(String stock_name) {

		init();

		List<StockEntity> resultList = entityManager.createQuery("from StockEntity ee where ee.stockName = :name")
				.setParameter("name", stock_name).getResultList();

		close();

		return resultList;
	}

	public List<StockEntity> getEntities(String stock_name, Date date) {

		init();

		List<StockEntity> resultList = entityManager
				.createQuery("from StockEntity ee where ee.stockName = :name and ee.date = :date")
				.setParameter("name", stock_name).setParameter("date", date).getResultList();

		close();

		return resultList;
	}

	public List<Date> getFirstAndLastDays() {

		List<Date> result = new ArrayList<Date>();

		init();

		List<Date> resultListMin = entityManager.createQuery("select min(ee.date)from StockEntity ee").getResultList();
		List<Date> resultListMax = entityManager.createQuery("select max(ee.date)from StockEntity ee").getResultList();

		result.add(resultListMin.get(FIRST_VALUE));
		result.add(resultListMax.get(FIRST_VALUE));

		close();

		return result;
	}

	private StockEntity getEmployee() {

		init();

		StockEntity aE = entityManager.find(StockEntity.class, FIRST_KEY);

		close();

		return aE;
	}

	private void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
