package by.epam.totalizator.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 8734714214640688834L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
