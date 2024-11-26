package guc.supermarket.exceptions;

@SuppressWarnings("serial")
public class CannotBuyException extends Exception {

	public CannotBuyException() {

	}

	public CannotBuyException(String msg) {

		super(msg);

	}

}
