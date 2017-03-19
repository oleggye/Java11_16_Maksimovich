package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class TakeDataForCompetition implements Command {

	private static final String DATA_FOR_COMPETITION_PAGE_URL = "path.page.dataForCompetition";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(DATA_FOR_COMPETITION_PAGE_URL);

		SessionRequestContent requestContent = new SessionRequestContent();
		requestContent.extractValues(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {
			factory.getCompetitionService().takeDataForCompetition(requestContent);
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
