package by.training.notes.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 3916298684740590291L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Exception cause) {
		super(message, cause);
	}
}
