package guc.supermarket.exceptions;

@SuppressWarnings("serial")
public class IncorrectSugarLevelException extends CannotBuyException {

	public IncorrectSugarLevelException() {

	}

	public IncorrectSugarLevelException(String msg) {

		super(msg);

	}

}
