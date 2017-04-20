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
import by.epam.totalizator.controller.util.PageKeyStore;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class UserProfilePage implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(CompetitionManagementPage.class.getName());
	/**
	 * url pattern for this command to put in user's session
	 */
	private static final String COMMAND_URL = "controller?command=user-profile";

	/**
	 * Performs a service level call and takes {@link User} by user's id
	 * 
	 * Sets these objects to the request and set {@link #COMMAND_URL} to the
	 * user's session as attributes.
	 * 
	 * If success sets attributes and forwards request and response to
	 * userProfile.jsp
	 * 
	 * If catch {@link ServiceException} than redirects to internalError.jsp
	 * 
	 * If catch {@link ServiceValidationException} than sets error message text
	 * as the request attribute and forwards request and response to
	 * previousPage from the user's session
	 * 
	 * @param request
	 *            contains a user request object from
	 *            {@link by.epam.totalizator.controller.Controller#processRequest}
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = null;

		Locale locale = getLocale(request);

		int IdUser = getIdUser(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			User user = factory.getClientService().getUserProfile(IdUser, locale);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER, user);

			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, COMMAND_URL);

			page = ConfigurationManager.getProperty(PageKeyStore.USER_PROFILE_PAGE_KEY);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			page = ConfigurationManager.getProperty(PageKeyStore.INTERNAL_ERROR_PAGE_KEY);

		} catch (ServiceValidationException e) {
			LOGGER.log(Level.WARN, e);
			page = (String) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * Method gets {@link java.util.Locale} from the user's session
	 */
	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	/**
	 * Method gets user's id from the request
	 */
	private int getIdUser(HttpServletRequest request) {
		return (Integer) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER);
	}
}
