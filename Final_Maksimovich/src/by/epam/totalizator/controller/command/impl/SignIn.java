package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

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
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class SignIn implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(SignIn.class.getName());

	/**
	 * url to home.jsp
	 */
	private static final String HOME_PAGE_URL = "controller?command=home";

	/**
	 * Takes login params to transfer to the service layer.
	 * 
	 * If success put user's param into the session and makes redirect to
	 * {@value #HOME_PAGE_URL}.
	 * 
	 * If catch {@link ServiceException} than redirects to internalError.jsp.
	 * 
	 * If catch {@link ServiceValidationException} or than sets error message
	 * text as the request attribute and forwards request and response to
	 * signIn.jsp
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

		String login = request.getParameter(ParamNameStore.PARAM_NAME_LOGIN);
		String password = request.getParameter(ParamNameStore.PARAM_NAME_PASSWORD);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {

			User user = factory.getClientService().signIn(login, password);

			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER, user.getId());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOGIN, user.getEmail());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_CURRENCY, user.getCurrency());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE, user.getBalance());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER_TYPE, user.getUserType());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL, user.getLocale());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BANNED, user.isBanned());

			page = HOME_PAGE_URL;
			response.sendRedirect(page);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			page = ConfigurationManager.getProperty(PageKeyStore.INTERNAL_ERROR_PAGE_KEY);
			request.getRequestDispatcher(page).forward(request, response);

		} catch (ServiceValidationException e) {
			LOGGER.log(Level.WARN, e);
			page = ConfigurationManager.getProperty(PageKeyStore.SIGN_IN_PAGE_KEY);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(page).forward(request, response);
		}
	}
}
