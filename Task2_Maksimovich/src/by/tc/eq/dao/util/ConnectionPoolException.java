package by.tc.eq.dao.util;

public class ConnectionPoolException extends Exception {


	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

}
