package by.tc.parser.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.parser.service.WebAppBuilder;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.factory.WebAppServiceFactory;

public class Controller {

	private static final String ERROR_MESSAGE = "Can't parse this file";

	private static final Logger logger = LogManager.getLogger(Controller.class.getName());

	public String parse(String parserType, final String fileName) {

		String response = null;

		WebAppServiceFactory factory = WebAppServiceFactory.getInstance();

		try {
			WebAppBuilder builder = factory.getWebAppBuilder(parserType);

			builder.buildWebApp(fileName);

			response = builder.getWebApp().toString();

		} catch (ServiceException e) {
			logger.error(e);
			response = ERROR_MESSAGE;
		}
		return response;
	}

}
