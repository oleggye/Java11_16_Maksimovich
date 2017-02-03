package by.tc.parser.start;

import by.tc.parser.controller.Controller;

public class Main {

	private static final String XML_URI = "C:/Users/hello/workspace/XMLParser/src/by/tc/parser/web.xml";

	public static void main(String[] args) {

		Controller controller = new Controller();

		String response = controller.parse("stax", XML_URI);

		System.out.println(response);
	}
}
