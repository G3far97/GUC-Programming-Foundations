package guc.supermarket.exceptions;

@SuppressWarnings("serial")
public class IncorrectFatTypeException extends CannotBuyException {

	public IncorrectFatTypeException() {

	}

	public IncorrectFatTypeException(String msg) {
		
		super(msg);
		
	}

}
