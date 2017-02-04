package by.tc.parser.bean;

import java.io.Serializable;

public class ErrorPage implements Serializable {

	private static final long serialVersionUID = -6015425758849961153L;

	private String exceptionType;

	private String errorCode;

	private String location;

	public ErrorPage() {
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "exceptionType= '" + exceptionType + "'; errorCode= '" + errorCode + "'; location= '" + location + "';";
	}

}
