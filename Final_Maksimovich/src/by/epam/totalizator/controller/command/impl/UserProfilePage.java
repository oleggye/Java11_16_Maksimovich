package by.epam.totalizator.controller.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class UserProfilePage implements ICommand {

	private static final Logger logger = LogManager.getLogger(CompetitionManagementPage.class.getName());

	private static final String COMMAND_URL_PATTERN = "controller?command=user-profile";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = null;

		Locale locale = getLocale(request);

		int IdUser = getIdUser(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			User user = factory.getClientService().getUserProfile(IdUser, locale);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER, user);

			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, COMMAND_URL_PATTERN);

			page = ConfigurationManager.getProperty(PageNameStore.USER_PROFILE_PAGE_URL);
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			page = ConfigurationManager.getProperty(PageNameStore.INTERNAL_ERROR_PAGE_URL);
			response.sendRedirect(page);

		} catch (ServiceValidationException e) {
			logger.log(Level.ERROR, e);
			page = (String) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			response.sendRedirect(page);
		}
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	private int getIdUser(HttpServletRequest request) {
		return (Integer) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER);
	}
}
