package org.capgemini.stock_exchange.main;

import org.capgemini.stock_exchange.exception.TryingToSellUnavailableStockException;
import org.capgemini.stock_exchange.player.Player;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws TryingToSellUnavailableStockException {

		Main p = new Main();
		p.start(args);
	}

	private void start(String[] args) throws TryingToSellUnavailableStockException {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
		Player player = (Player) context.getBean("player");
		player.play();
	}

}
