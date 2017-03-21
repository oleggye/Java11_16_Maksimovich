package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SignIn implements Command {

	private static final String HOME_PAGE_LINK = "controller?command=home";
	private static final String HOME_PAGE_URL = "path.page.home";
	private static final String SIGNIN_PAGE_URL = "path.page.signIn";
	private static final String INTERNAL_ERROR_PAGE_URL = "path.page.internalError";
	private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;

		SessionRequestContent requestContent = new SessionRequestContent();
		requestContent.extractValues(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {

			factory.getClientService().signIn(requestContent);

			requestContent.insertAttributeMap(request);

			// page = ConfigurationManager.getProperty(HOME_PAGE_URL);
			page = HOME_PAGE_LINK;

		} catch (ServiceException e) {
			// TODO: logging
			page = ConfigurationManager.getProperty(INTERNAL_ERROR_PAGE_URL);

		} catch (ServiceValidationException e) {
			// TODO: logging
			request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			page = ConfigurationManager.getProperty(SIGNIN_PAGE_URL);
		}
		return page;
	}

}
