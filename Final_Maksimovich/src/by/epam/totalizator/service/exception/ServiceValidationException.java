package by.epam.totalizator.service.exception;

import java.util.Set;

public class ServiceValidationException extends Exception {

	private static final long serialVersionUID = -7869705393498571745L;

	private Set<String> errorSet;

	public ServiceValidationException(Set<String> errorSet) {
		this.errorSet = errorSet;
	}

	public Set<String> getErrorSet() {
		return errorSet;
	}

	public ServiceValidationException(String message) {
		super(message);
	}

	/*
	 * public ServiceValidationException(String message, Throwable cause) {
	 * super(message, cause); }
	 */

}
