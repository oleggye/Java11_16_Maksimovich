package by.epam.totalizator.dao.pool.exception;

/**
 * An exception that provides information about violation in the work of the
 * connection pool {@link by.epam.totalizator.dao.pool.IConnectionPool}
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = -8920347865349191827L;

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}
}
