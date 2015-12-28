package org.capgemini.stock_exchange.service;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.capgemini.stock_exchange.entity.StockEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class StockServiceTest {
	
	@Autowired
	StockService service;// = new StockService();
	
	DateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	@Test
	public void shouldGetEntities() throws ParseException{
		
		String name = "INTEL";
		String stringDate = "20011024";
		Date date = format.parse(stringDate);
		
		List<StockEntity> allEntities = service.getEntities(name, date);
		
		assertNotNull(allEntities);
		for(StockEntity entity: allEntities){
			System.out.println(entity.getStockName() + " " + entity.getDate());
		}
	}
	
	@Test
	public void shouldGetDataByDate() throws ParseException{
		
		String stringDate = "20011024";
		Date date = format.parse(stringDate);
		List<StockEntity> allEntities = service.getEntitiesByDate(date);
		
		assertNotNull(allEntities);
	}
	
	@Test
	public void shouldGetDataByName(){
		
		String name = "INTEL";
		List<StockEntity> allEntities = service.getEntitiesByName(name);
		
		assertNotNull(allEntities);
		for(StockEntity entity: allEntities){
			System.out.println(entity.getStockName() + " " + entity.getDate());
		}
	}

}
