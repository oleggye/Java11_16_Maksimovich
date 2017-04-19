package by.epam.totalizator.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 7467583520380780476L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}