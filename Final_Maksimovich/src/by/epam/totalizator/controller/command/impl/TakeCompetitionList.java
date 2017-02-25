package by.epam.totalizator.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class TakeCompetitionList implements Command {

	private static final String PARAM_NAME_ID_SPORT = "idsport";
	private static final String PARAM_NAME_ID_TOURNAMENT = "idtourn";
	private static final String PARAM_NAME_PAGE_NUMBER = "pageNumber";
	private static final int RECORDS_PER_PAGE = 4;
	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final String ATTRIBUTE_NAME_COMPETITIONS = "competitions";
	private static final String ATTRIBUTE_NAME_PAGE_COUNT = "count";
	private static final String ATTRIBUTE_NAME_PAGE_NUMBER = "pageNumber";
	private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
	private static final String COMPETITION_PAGE_URL = "path.page.competition";
	private static final String ERROR_PAGE_URL = "path.page.error";
	private static final String ATTRIBUTE_NAME_COMMAND = "command";
	private static final String ATTRIBUTE_COMMAND_VALUE = "competition";

	@Override
	public String execute(HttpServletRequest request) {

		String page;

		String idSportParam = request.getParameter(PARAM_NAME_ID_SPORT);
		String idTournamentParam = request.getParameter(PARAM_NAME_ID_TOURNAMENT);
		String pageNumberParam = request.getParameter(PARAM_NAME_PAGE_NUMBER);

		int pageNumber;

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			pageNumber = Integer.valueOf(pageNumberParam);
		}

		int idSport = 0;
		int idTournament = 0;

		if (idSportParam != null && idTournamentParam != null) {

			idSport = Integer.valueOf(idSportParam);
			idTournament = Integer.valueOf(idTournamentParam);
			// TODO: првоерять лучше здесь, хотя можно и в сервисах

			ServiceFactory factory = ServiceFactory.getInstance();
			try {
				List<Competition> competitions = factory.getCompetitionService().obtainSpecialCompetitionsList(idSport,
						idTournament, pageNumber, RECORDS_PER_PAGE);
				request.setAttribute(ATTRIBUTE_NAME_COMPETITIONS, competitions);

				page = ConfigurationManager.getProperty(COMPETITION_PAGE_URL);
				int specialCount = factory.getCompetitionService().obtainSpecialCompetitionsCount(idSport,
						idTournament);

				double result = (double) specialCount / RECORDS_PER_PAGE;
				// TODO: предполагается, что long многова-то
				int pageCount = (int) Math.ceil(result);

				request.setAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
				request.setAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);
				request.setAttribute(ATTRIBUTE_NAME_COMMAND, ATTRIBUTE_COMMAND_VALUE);

			} catch (ServiceException e) {
				// TODO:logging
				page = ConfigurationManager.getProperty(ERROR_PAGE_URL);
				request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			}
		} else
			page = ConfigurationManager.getProperty(ERROR_PAGE_URL);

		return page;
	}

}
