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

import javax.annotation.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "RepositoryTest-context.xml")
public class StockRepositoryTest {
	
	private final static Logger LOGGER = Logger.getLogger(StockRepositoryTest.class.getName());
	
	@Autowired
	StockRepository repository;

//	private List<StockEntity> entities;
//	
//	@Before
//	public void init(){
////		data = repository.importData();
////		repository.init();
//		entities = repository.getData();
//	}
//		
//	@Test
//	public void shouldReturnExistingEntities() {
//		
//		assertNotNull(entities);
//		
//		LOGGER.log(Level.INFO, entities.get(0).getName() + entities.get(0).getDate() + entities.get(0).getCost());
//		LOGGER.log(Level.INFO, Integer.toString(entities.size()));
//	}
//	
//	@Test
//	public void shouldGetByName() {
//		
//		String name = "INTEL";
//		
//		List<StockEntity> specifiedEntities = repository.getEntitiesByName(name);
//		
//		for(StockEntity entity: specifiedEntities){
//			assertEquals(name, entity.getName());
//		}
//	}
}
