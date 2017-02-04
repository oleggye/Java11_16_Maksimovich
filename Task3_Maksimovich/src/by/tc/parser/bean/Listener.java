package by.tc.parser.bean;

import java.io.Serializable;

public class Listener implements Serializable {

	private static final long serialVersionUID = -863590622759229855L;

	private String listenerClass;

	public Listener() {

	}

	public String getListenerClass() {
		return listenerClass;
	}

	public void setListenerClass(String listenerClass) {
		this.listenerClass = listenerClass;
	}

	@Override
	public String toString() {
		return "listenerClass= '" + listenerClass + "';";
	}

}
