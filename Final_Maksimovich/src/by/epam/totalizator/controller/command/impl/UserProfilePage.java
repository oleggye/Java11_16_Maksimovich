package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class UserProfilePage implements Command {

	private static final String USER_PROFILE_PAGE_URL = "path.page.userProfile";
	private static final String INTERNAL_ERROR_PAGE_URL = "path.page.internalError";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;

		SessionRequestContent requestContent = new SessionRequestContent();
		requestContent.extractValues(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			factory.getClientService().getUserProfile(requestContent);

			requestContent.insertAttributeMap(request);
			page = ConfigurationManager.getProperty(USER_PROFILE_PAGE_URL);

		} catch (ServiceException e) {
			// TODO: logging
			page = ConfigurationManager.getProperty(INTERNAL_ERROR_PAGE_URL);
		}

		return page;
	}

}
