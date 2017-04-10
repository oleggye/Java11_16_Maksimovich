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
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class AddCompetition implements ICommand {

	private static final Logger logger = LogManager.getLogger(AddCompetition.class.getName());

	private static final String RESPONSE_CONTENT_TYPE = "text/plain";

	private static final String SUCCESS_MESSAGE_KEY = "local.ajax.add_competition.success";
	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType(RESPONSE_CONTENT_TYPE);

		String message = null;
		int statusCode;

		Locale locale = getLocale(request);

		try {
			Competition competition = parseRequestParam(request);
			ServiceFactory factory = ServiceFactory.getInstance();

			factory.getCompetitionService().addCompetition(competition);

			statusCode = HttpServletResponse.SC_OK;
			message = LocalizationBundle.getInstance().getProperty(locale, SUCCESS_MESSAGE_KEY);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = LocalizationBundle.getInstance().getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | IllegalArgumentException e) {
			logger.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message = LocalizationBundle.getInstance().getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();
		}
		sendMessage(response, statusCode, message);
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	private void sendMessage(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}

	private Competition parseRequestParam(HttpServletRequest request) {
		
		Date startTime = new Date(request.getParameter(ParamNameStore.PARAM_NAME_DATE));
		int idSport = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_SPORT));
		int idHomeClub = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_HOME_CLUB));
		int idAwayClub = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_AWAY_CLUB));
		int idTournament = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_TOURNAMENT));
		int idCountry = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_COUNTRY));

		Sport sport = new SportBuilder().buildId(idSport).build();

		Club homeClub = new ClubBuilder().buildId(idHomeClub).build();
		Team homeTeam = new TeamBuilder().buildClub(homeClub).build();

		Club awayClub = new ClubBuilder().buildId(idAwayClub).build();
		Team awayTeam = new TeamBuilder().buildClub(awayClub).build();

		Tournament tournament = new TournamentBuilder().buildId(idTournament).build();
		Country country = new CountryBuilder().buildId(idCountry).build();

		Competition competition = new CompetitionBuilder().buildStartTime(startTime).buildSport(sport)
				.buildHomeTeam(homeTeam).buildAwayTeam(awayTeam).buildTournament(tournament).buildCountry(country)
				.build();
		return competition;
	}
}
