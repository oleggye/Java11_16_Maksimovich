package by.tc.parser.bean;

import java.io.Serializable;

public class InitParam implements Serializable {

	private static final long serialVersionUID = -5091872163277864843L;

	private String paramName;

	private String paramValue;

	public InitParam() {
	}

	public String getParamName() {
		return paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String toString() {
		return "paramName= '" + paramName + "', paramValue= '" + paramValue + "';";
	}
}
