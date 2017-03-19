package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class UserBettingPage implements Command {

	private static final String USER_BETTING_PAGE_URL = "path.page.userBetting";
	private static final String ERROR_PAGE_URL = "path.page.error";
	private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
	private static final String INTERNAL_ERROR_PAGE_URL = "path.page.internalError";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;

		SessionRequestContent requestContent = new SessionRequestContent();
		requestContent.extractValues(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			factory.getBettingService().obtainUserBettingList(requestContent);

			requestContent.insertAttributeMap(request);
			page = ConfigurationManager.getProperty(USER_BETTING_PAGE_URL);

		} catch (ServiceException e) {
			// TODO: logging
			page = ConfigurationManager.getProperty(INTERNAL_ERROR_PAGE_URL);

		} catch (ServiceValidationException e) {
			// TODO logging и дописать!!!
			page = ConfigurationManager.getProperty(ERROR_PAGE_URL);
			request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
		}

		return page;
	}

}
