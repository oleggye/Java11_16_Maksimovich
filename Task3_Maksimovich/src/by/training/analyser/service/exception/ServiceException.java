package by.training.analyser.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 6120949656080036099L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
}
