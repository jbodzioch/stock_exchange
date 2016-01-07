package org.capgemini.stock_exchange.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.capgemini.stock_exchange.stock_loader.StockLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class StockRepository {

	@Autowired
	private StockLoader stockLoader;

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private final static Long FIRST_KEY = 1L;

	// TODO JBODZIOCH spytaj o factory
	@PostConstruct
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("plain-jpa");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@PostConstruct
	public void checkDatabase() {

		if (getEmployee() == null) {
			stockLoader.importData();
		}
	}

	public List<StockEntity> getEntitiesByDate(Date date) {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("plain-jpa");
		// entityManager = entityManagerFactory.createEntityManager();
		List<StockEntity> resultList = entityManager.createQuery("from StockEntity ee where ee.date = :last")
				.setParameter("last", date).getResultList();

		return resultList;
	}

	public List<StockEntity> getEntitiesByName(String stock_name) {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("plain-jpa");
		// entityManager = entityManagerFactory.createEntityManager();
		List<StockEntity> resultList = entityManager.createQuery("from StockEntity ee where ee.stockName = :first")
				.setParameter("first", stock_name).getResultList();

		return resultList;
	}

	public List<StockEntity> getEntities(String stock_name, Date date) {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("plain-jpa");
		// entityManager = entityManagerFactory.createEntityManager();
		List<StockEntity> resultList = entityManager
				.createQuery("from StockEntity ee where ee.stockName = :first and ee.date = :last")
				.setParameter("first", stock_name).setParameter("last", date).getResultList();

		return resultList;
	}

	public List<Date> getFirstAndLastDays() {
		List<Date> result = new ArrayList<Date>();
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("plain-jpa");
		// entityManager = entityManagerFactory.createEntityManager();
		List<Date> resultListMin = entityManager.createQuery("select min(ee.date)from StockEntity ee").getResultList();

		List<Date> resultListMax = entityManager.createQuery("select max(ee.date)from StockEntity ee").getResultList();

		result.add(resultListMin.get(0));
		result.add(resultListMax.get(0));

		return result;
	}

	private StockEntity getEmployee() {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("plain-jpa");
		// entityManager = entityManagerFactory.createEntityManager();
		StockEntity aE = entityManager.find(StockEntity.class, FIRST_KEY);

		return aE;
	}

	@PreDestroy
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
