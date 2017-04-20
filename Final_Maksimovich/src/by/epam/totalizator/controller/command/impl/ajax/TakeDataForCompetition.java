package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.controller.util.SupportClass;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class TakeDataForCompetition implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(TakeDataForCompetition.class.getName());

	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	private static final String RESPONSE_JSON_CONTENT_TYPE = "application/json";
	private static final String RESPONSE_TEXT_CONTENT_TYPE = "text/plain";

	/**
	 * Performs a service level call and takes {@link java.util.List} of
	 * {@link Tournament}, {@link Team} and {@link Country}.
	 * 
	 * Serializes this objects and sends it back.
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
			int idSport = getIdSport(request);

			List<Tournament> tournamentList = factory.getTournamentService().obtainTournamentListByIdSport(idSport,
					locale);
			List<Team> teamList = factory.getTeamService().obtainTeamListByIdSport(idSport, locale);
			List<Country> countryList = factory.getCountryService().obtainCountryList(locale);

			List<Object> attributeList = new ArrayList<>(3);

			attributeList.add(tournamentList);
			attributeList.add(teamList);
			attributeList.add(countryList);

			message = new Gson().toJson(attributeList);
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
	 * {@link by.epam.totalizator.controller.util.SupportClass#parseId}
	 */
	private int getIdSport(HttpServletRequest request) {
		String idSportParam = request.getParameter(ParamNameStore.PARAM_NAME_ID_SPORT);
		return SupportClass.parseId(idSportParam);
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
