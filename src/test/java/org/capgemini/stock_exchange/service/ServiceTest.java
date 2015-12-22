package org.capgemini.stock_exchange.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class ServiceTest {
	
	@Autowired
	StockService service;// = new StockService();
	
	@Test
	public void shouldGetAllThings(){
		List<StockEntity> allEntities = service.getAllEntities();
		
		assertNotNull(allEntities);
	}
	
	@Test
	public void shouldGetDataByDate(){
		
		int date = 20130102;
		List<StockEntity> allEntities = service.getEntitiesByDate(date, service.getAllEntities());
		
		assertNotNull(allEntities);
	}
	
	@Test
	public void shouldGetDataByName(){
		
		String name = "PKOBP";
		List<StockEntity> allEntities = service.getEntitiesByName(name, service.getAllEntities());
		
		assertNotNull(allEntities);
	}

}
