package by.tc.parser.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1249058770733388435L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
