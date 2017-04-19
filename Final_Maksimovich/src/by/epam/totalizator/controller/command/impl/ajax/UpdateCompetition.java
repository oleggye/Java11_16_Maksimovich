package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.bean.build.ClubBuilder;
import by.epam.totalizator.bean.build.CompetitionBuilder;
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.bean.build.SportBuilder;
import by.epam.totalizator.bean.build.TeamBuilder;
import by.epam.totalizator.bean.build.TournamentBuilder;
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
public class UpdateCompetition implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(UpdateCompetition.class.getName());

	private static final String RESPONSE_CONTENT_TYPE = "text/plain";

	private static final String SUCCESS_MESSAGE_KEY = "local.ajax.update_competition.success";
	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	/**
	 * Forms {@link Competition} bean for competition updating and performs a
	 * service level call.
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
		int statusCode;

		Locale locale = getLocale(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {
			Competition competition = parseRequestParam(request);

			factory.getCompetitionService().updateCompetition(competition);

			statusCode = HttpServletResponse.SC_OK;
			message = LocalizationBundle.getProperty(locale, SUCCESS_MESSAGE_KEY);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = LocalizationBundle.getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | IllegalArgumentException e) {
			LOGGER.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message = LocalizationBundle.getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();

		}
		sendMessage(response, statusCode, message);
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

	/**
	 * Method gets {@link Competition} bean from request
	 */
	private Competition parseRequestParam(HttpServletRequest request) {

		Date startTime = getDate(request);
		int idCompetition = getId(request, ParamNameStore.PARAM_NAME_ID_COMPETITION);
		int idSport = getId(request, ParamNameStore.PARAM_NAME_ID_SPORT);
		int idHomeClub = getId(request, ParamNameStore.PARAM_NAME_ID_HOME_CLUB);
		int idAwayClub = getId(request, ParamNameStore.PARAM_NAME_ID_AWAY_CLUB);
		int idTournament = getId(request, ParamNameStore.PARAM_NAME_ID_TOURNAMENT);
		int idCountry = getId(request, ParamNameStore.PARAM_NAME_ID_COUNTRY);

		String resultParam = request.getParameter(ParamNameStore.PARAM_NAME_RESULT);

		Sport sport = new SportBuilder().buildId(idSport).build();

		Club homeClub = new ClubBuilder().buildId(idHomeClub).build();
		Team homeTeam = new TeamBuilder().buildClub(homeClub).build();

		Club awayClub = new ClubBuilder().buildId(idAwayClub).build();
		Team awayTeam = new TeamBuilder().buildClub(awayClub).build();

		Tournament tournament = new TournamentBuilder().buildId(idTournament).build();
		Country country = new CountryBuilder().buildId(idCountry).build();
		EventType result = null;
		if (resultParam != null) {
			result = EventType.getTypeByShortName(resultParam);
		}

		Competition competition = new CompetitionBuilder().buildId(idCompetition).buildStartTime(startTime)
				.buildSport(sport).buildHomeTeam(homeTeam).buildAwayTeam(awayTeam).buildTournament(tournament)
				.buildCountry(country).buildResult(result).build();

		return competition;
	}

	/**
	 * Method gets date string from the user's request and delegate safety
	 * parsing of it to
	 * {@link by.epam.totalizator.controller.util.SupportClass#parseDate}
	 */
	private Date getDate(HttpServletRequest request) {
		String dateParam = request.getParameter(ParamNameStore.PARAM_NAME_DATE);
		return SupportClass.parseDate(dateParam);
	}

	/**
	 * Method gets id string from the user's request by key and delegate safety
	 * parsing of it to
	 * {@link by.epam.totalizator.controller.util.SupportClass#parseDate}
	 */
	private int getId(HttpServletRequest request, String key) {
		String idParam = request.getParameter(key);
		return SupportClass.parseId(idParam);
	}
}
