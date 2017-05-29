package by.epam.totalizator.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Country;
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
public class SignUpPage implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(SignUpPage.class.getName());

	/**
	 * url pattern for this command to put in user's session
	 */
	private static final String COMMAND_URL = "controller?command=signup-page";

	/**
	 * Performs a service level call and takes {@link java.util.List} of
	 * {@link Country} and obtain country count.
	 * 
	 * Sets these objects to the request and {@value #COMMAND_URL} to the user's
	 * session as attributes.
	 * 
	 * If success sets attributes and forwards request and response to
	 * signUp.jsp
	 * 
	 * If catch {@link ServiceException} than redirects to internalError.jsp
	 * 
	 * If catch {@link ServiceValidationException} than sets error message text
	 * as the request attribute and forwards request and response to a
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

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			List<Country> countryList = factory.getCountryService().obtainCountryList(locale);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_COUNTRY_LIST, countryList);

			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, COMMAND_URL);

			page = ConfigurationManager.getProperty(PageKeyStore.SIGN_UP_PAGE_KEY);
			request.getRequestDispatcher(page).forward(request, response);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}	
	}

	/**
	 * Method gets {@link java.util.Locale} from the user's session
	 */
	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}
}