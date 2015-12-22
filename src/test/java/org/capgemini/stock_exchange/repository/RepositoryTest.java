package org.capgemini.stock_exchange.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "RepositoryTest-context.xml")
public class RepositoryTest {
	
	private final static Logger LOGGER = Logger.getLogger(RepositoryTest.class.getName());
	
	@Autowired
	StockRepository repository;

	private List<String> data;
	private List<StockEntity> entities;
	
	@Before
	public void init(){
		data = repository.importData();
		entities = repository.generateEntities(data);
	}
	
	@Test
	public void shouldReadDataFromFile() {
		
		assertNotNull(data);
		
		LOGGER.log(Level.INFO, data.get(0));
		LOGGER.log(Level.INFO, Integer.toString(data.size()));
	}
	
	@Test
	public void shouldReturnExistingEntities() {
		
		assertNotNull(entities);
		
		LOGGER.log(Level.INFO, entities.get(0).getName() + entities.get(0).getDate() + entities.get(0).getCost());
		LOGGER.log(Level.INFO, Integer.toString(entities.size()));
	}
	
	@Test
	public void shouldGetByName() {
		
		String name = "INTEL";
		
		List<StockEntity> specifiedEntities = repository.getEntitiesByName(name, entities);
		
		for(StockEntity entity: specifiedEntities){
			assertEquals(name, entity.getName());
		}
	}
	
	@Test
	public void shouldSortByDate() {
		
		String name = "INTEL";
		
		List<StockEntity> entities2 = repository.getEntitiesByName(name, entities);
		
		List<StockEntity> specifiedEntities = repository.sortEntitiesByDate(entities2);
		
		assertNotNull(specifiedEntities);
		
		StockEntity temp = new StockEntity(null, 0, 0);
		
		for(StockEntity entity: specifiedEntities){
			assertTrue(entity.getDate()>=temp.getDate());
			temp = entity;
		}
	}
}
