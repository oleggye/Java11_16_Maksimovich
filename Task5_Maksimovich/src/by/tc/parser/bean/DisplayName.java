package by.tc.parser.bean;

import java.io.Serializable;

public class DisplayName implements Serializable {

	private static final long serialVersionUID = -7137203378135420052L;

	private String text;

	public DisplayName() {

	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "text= '" + text + "'";
	}
}
