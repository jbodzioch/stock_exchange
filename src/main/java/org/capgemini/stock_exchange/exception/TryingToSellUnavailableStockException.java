package org.capgemini.stock_exchange.exception;

@SuppressWarnings("serial")
public class TryingToSellUnavailableStockException extends Exception {
	public TryingToSellUnavailableStockException() {
		super();
	}

	public TryingToSellUnavailableStockException(String message) {
		super(message);
	}

	public TryingToSellUnavailableStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public TryingToSellUnavailableStockException(Throwable cause) {
		super(cause);
	}
}
