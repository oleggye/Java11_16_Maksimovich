package by.epam.totalizator.dao.pool.exception;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = -8920347865349191827L;

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}
}
