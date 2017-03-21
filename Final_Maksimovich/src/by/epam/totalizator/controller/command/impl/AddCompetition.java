package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class AddCompetition implements Command {

	private static final String RESPONSE_PAGE_URL = "path.page.ajaxResponse";

	@Override
	public String execute(HttpServletRequest request) {

		String page = ConfigurationManager.getProperty(RESPONSE_PAGE_URL);

		SessionRequestContent requestContent = new SessionRequestContent();
		requestContent.extractValues(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {
			factory.getCompetitionService().addCompetition(requestContent);
			requestContent.insertAttributeMap(request);

		} catch (ServiceException e) {
			// TODO: logging
			// TODO: нужно оправлять ошибку по ajax

		} catch (ServiceValidationException e) {
			// TODO logging и дописать!!!
			// TODO: нужно оправлять ошибку по ajax
		}
		return page;
	}
}
