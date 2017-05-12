package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.controller.util.UtilClass;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class UnbanUser implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(UnbanUser.class.getName());

	private static final String RESPONSE_CONTENT_TYPE = "text/plain";

	private static final String SUCCESS_MESSAGE_KEY = "local.ajax.unban_user";
	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	/**
	 * Performs a service level call to unban a user by id.
	 * 
	 * If success receives a message by key {@value #SUCCESS_MESSAGE_KEY} and
	 * sends it back.
	 * 
	 * If catch {@link ServiceException} than receives a message by key
	 * {@value #INTERNAL_SERVER_ERROR_MESSAGE_KEY} and sends it back.
	 * 
	 * If catch {@link ServiceValidationException} than receives a message by
	 * key {@value #WRONG_PARAM_MESSAGE_KEY} and sends it back.
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
		response.setContentType(RESPONSE_CONTENT_TYPE);

		String message = null;
		int statusCode = 0;

		Locale locale = getLocale(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {
			int idUser = getIdUser(request);

			factory.getClientService().unbanUser(idUser);

			statusCode = HttpServletResponse.SC_OK;
			message = LocalizationBundle.getProperty(locale, SUCCESS_MESSAGE_KEY);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = LocalizationBundle.getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | NumberFormatException e) {
			LOGGER.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message = LocalizationBundle.getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();
		}
		sendMessage(response, statusCode, message);
	}

	/**
	 * Method gets id string from the user's request by key and delegate safety
	 * parsing of it to
	 * {@link by.epam.totalizator.controller.util.UtilClass#parseDate}
	 */
	private int getIdUser(HttpServletRequest request) {
		String idParam = request.getParameter(ParamNameStore.PARAM_NAME_ID_USER);
		return UtilClass.parseId(idParam);
	}

	/**
	 * Method forms {@link java.util.Locale} from the user's session
	 */
	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	/**
	 * The method generates a response to the user
	 */
	private void sendMessage(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}
}
