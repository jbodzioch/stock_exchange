package org.capgemini.stock_exchange.player;

import org.capgemini.stock_exchange.exception.TryingToSellUnavailableStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "PlayerTest-context.xml")
public class PlayerTest {

	@Autowired
	private Player player;

	@Test
	public void shouldProperlyProcessPlay() throws TryingToSellUnavailableStockException {
		player.play();
	}

}
