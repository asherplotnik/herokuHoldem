package app.core.exceptions;

public class HoldemException extends Exception {

	private static final long serialVersionUID = 1L;

	public HoldemException() {
		super();
	}

	public HoldemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HoldemException(String message, Throwable cause) {
		super(message, cause);
	}

	public HoldemException(String message) {
		super(message);
	}

	public HoldemException(Throwable cause) {
		super(cause);
	}
	

}
