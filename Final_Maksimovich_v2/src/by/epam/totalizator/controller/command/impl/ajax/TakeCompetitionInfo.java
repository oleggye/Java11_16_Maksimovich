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

import com.google.gson.Gson;

import by.epam.totalizator.bean.Competition;
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
public class TakeCompetitionInfo implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(TakeCompetitionInfo.class.getName());

	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	private static final String RESPONSE_JSON_CONTENT_TYPE = "application/json";
	private static final String RESPONSE_TEXT_CONTENT_TYPE = "text/plain";

	/**
	 * Performs a service level call and takes {@link Competition} by id
	 * 
	 * Serializes this bean and sends it back.
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

		String message = null;
		int statusCode;

		Locale locale = getLocale(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			int idCompetition = getIdCompetition(request);

			Competition competition = factory.getCompetitionService().obtainCompetition(idCompetition, locale);

			message = new Gson().toJson(competition);
			statusCode = HttpServletResponse.SC_OK;
			response.setContentType(RESPONSE_JSON_CONTENT_TYPE);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			response.setContentType(RESPONSE_TEXT_CONTENT_TYPE);
			message = LocalizationBundle.getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | NumberFormatException e) {
			LOGGER.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			response.setContentType(RESPONSE_TEXT_CONTENT_TYPE);
			message = LocalizationBundle.getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();
		}
		sendMessage(response, statusCode, message);
	}

	/**
	 * Method gets id string from the user's request by key and delegate safety
	 * parsing of it to
	 * {@link by.epam.totalizator.controller.util.UtilClass#parseId}
	 */
	private int getIdCompetition(HttpServletRequest request) {
		String idCompetitionParam = request.getParameter(ParamNameStore.PARAM_NAME_ID_COMPETITION);
		return UtilClass.parseId(idCompetitionParam);
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
