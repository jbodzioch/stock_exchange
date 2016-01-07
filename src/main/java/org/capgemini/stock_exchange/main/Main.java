package org.capgemini.stock_exchange.main;

import org.capgemini.stock_exchange.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

//@Component
//@ContextConfiguration(locations = "spring-context.xml")
public class Main {
	
	static Player player = new Player();
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-context.xml ");
		
		player.play();
	}

}
