package by.epam.totalizator.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.CompetitionService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;
import by.epam.totalizator.service.util.SupportClass;
import by.epam.totalizator.util.build.ClubBuilder;
import by.epam.totalizator.util.build.CompetitionBuilder;
import by.epam.totalizator.util.build.CountryBuilder;
import by.epam.totalizator.util.build.SportBuilder;
import by.epam.totalizator.util.build.TeamBuilder;
import by.epam.totalizator.util.build.TournamentBuilder;

public class CompetitionServiceImpl implements CompetitionService {

	private static final String PARAM_NAME_PAGE_NUMBER = "pageNumber";
	private static final String PARAM_NAME_ID_SPORT = "idSport";
	private static final String PARAM_NAME_ID_HOME_CLUB = "idHomeClub";
	private static final String PARAM_NAME_ID_AWAY_CLUB = "idAwayClub";
	private static final String PARAM_NAME_ID_TOURNAMENT = "idTournament";
	private static final String PARAM_NAME_ID_COMPETITION = "idCompetition";
	private static final String PARAM_NAME_ID_COUNTRY = "idCountry";
	private static final String PARAM_NAME_DATE = "date";
	private static final int RECORDS_PER_PAGE = 5;
	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final String ATTRIBUTE_NAME_JSON = "json";
	private static final String ATTRIBUTE_NAME_COMPETITION_LIST = "competitionList";
	private static final String ATTRIBUTE_NAME_PAGE_COUNT = "count";
	private static final String ATTRIBUTE_NAME_PAGE_NUMBER = "pageNumber";

	@Override
	public void addCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {
		
		String startTimeParam= requestContent.getRequestParam(PARAM_NAME_DATE);
		String idSportParam= requestContent.getRequestParam(PARAM_NAME_ID_SPORT);
		String idHomeClubParam =requestContent.getRequestParam(PARAM_NAME_ID_HOME_CLUB);
		String idAwayClubParam =requestContent.getRequestParam(PARAM_NAME_ID_AWAY_CLUB);
		String idTournamentParam =requestContent.getRequestParam(PARAM_NAME_ID_TOURNAMENT);
		String idCountryParam =requestContent.getRequestParam(PARAM_NAME_ID_COUNTRY);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateStartTime(startTimeParam);
		serviceFactory.getValidationService().validateIdSport(idSportParam);
		serviceFactory.getValidationService().validateIdClubParam(idHomeClubParam);
		serviceFactory.getValidationService().validateIdClubParam(idAwayClubParam);
		serviceFactory.getValidationService().validateIdTournament(idTournamentParam);
		serviceFactory.getValidationService().validateIdCountryParam(idCountryParam);
		
		Date startTime = new Date(Date.parse(startTimeParam));
		System.out.println(startTime);
		int idSport = Integer.valueOf(idSportParam);
		int idHomeClub = Integer.valueOf(idHomeClubParam);
		int idAwayClub = Integer.valueOf(idAwayClubParam);
		int idTournament = Integer.valueOf(idTournamentParam);
		int idCountry = Integer.valueOf(idCountryParam);
		
		Sport sport = new SportBuilder()
							.buildId(idSport)
							.build();
		
		Club homeClub = new ClubBuilder()
							.buildId(idHomeClub)
							.build();
		Team homeTeam = new TeamBuilder()
								.buildClub(homeClub)
								.build();
		
		Club awayClub = new ClubBuilder()
							.buildId(idAwayClub)
							.build();
		Team awayTeam = new TeamBuilder()	
								.buildClub(awayClub)
								.build();
		
		Tournament tournament = new TournamentBuilder()
									.buildId(idTournament)
									.build();
		Country country = new CountryBuilder()
								.buildId(idCountry)
								.build();
		
		Competition competition = new CompetitionBuilder()
										.buildStartTime(startTime)
										.buildSport(sport)
										.buildHomeTeam(homeTeam)
										.buildAwayTeam(awayTeam)
										.buildTournament(tournament)
										.buildCountry(country)
										.build();
										
		
		DAOFactory factory = DAOFactory.getInstance();
		
		try {
			factory.getCompetitionDAO().addCompetition(competition);;
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}

	}

	@Override
	public void obtainCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String idCompetitionParam = requestContent.getRequestParam(PARAM_NAME_ID_COMPETITION);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdCompetition(idCompetitionParam);

		int idCompetition = Integer.valueOf(idCompetitionParam);

		DAOFactory factory = DAOFactory.getInstance();
		Competition competition = null;

		try {
			/*
			 * добавление соревнования и баланса пользователя в коллекцию и
			 * сериализация в JSON
			 */
			competition = factory.getCompetitionDAO().obtainCompetition(idCompetition);
			// Object userBallance =
			// requestContent.getSessionParam(PARAM_NAME_BALANCE);
			// TODO: заглушка!
			Object userBallance = 123.32;

			List<Object> attributeList = new ArrayList<>(2);
			attributeList.add(competition);
			attributeList.add(userBallance);

			String json = new Gson().toJson(attributeList);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_JSON, json);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	@Override
	public void updateCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {
		
		//TODO:ОШИБСЯ И НАПИСАЛ ОБРАБОТЧИК ДЛЯ addCompetition(...)!
		
		String startTimeParam= requestContent.getRequestParam(PARAM_NAME_DATE);
		String idSportParam= requestContent.getRequestParam(PARAM_NAME_ID_SPORT);
		String idHomeClubParam =requestContent.getRequestParam(PARAM_NAME_ID_HOME_CLUB);
		String idAwayClubParam =requestContent.getRequestParam(PARAM_NAME_ID_AWAY_CLUB);
		String idTournamentParam =requestContent.getRequestParam(PARAM_NAME_ID_TOURNAMENT);
		String idCountryParam =requestContent.getRequestParam(PARAM_NAME_ID_COUNTRY);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateStartTime(startTimeParam);
		serviceFactory.getValidationService().validateIdSport(idSportParam);
		serviceFactory.getValidationService().validateIdClubParam(idHomeClubParam);
		serviceFactory.getValidationService().validateIdClubParam(idAwayClubParam);
		serviceFactory.getValidationService().validateIdTournament(idTournamentParam);
		serviceFactory.getValidationService().validateIdCountryParam(idCountryParam);
		
		Date startTime = new Date(Date.parse(startTimeParam));
		int idSport = Integer.valueOf(idSportParam);
		int idHomeClub = Integer.valueOf(idHomeClubParam);
		int idAwayClub = Integer.valueOf(idAwayClubParam);
		int idTournament = Integer.valueOf(idTournamentParam);
		int idCountry = Integer.valueOf(idCountryParam);
		
		Sport sport = new SportBuilder()
							.buildId(idSport)
							.build();
		
		Club homeClub = new ClubBuilder()
							.buildId(idHomeClub)
							.build();
		Team homeTeam = new TeamBuilder()
								.buildClub(homeClub)
								.build();
		
		Club awayClub = new ClubBuilder()
							.buildId(idAwayClub)
							.build();
		Team awayTeam = new TeamBuilder()	
								.buildClub(awayClub)
								.build();
		
		Tournament tournament = new TournamentBuilder()
									.buildId(idTournament)
									.build();
		Country country = new CountryBuilder()
								.buildId(idCountry)
								.build();
		
		Competition competition = new CompetitionBuilder()
										.buildStartTime(startTime)
										.buildSport(sport)
										.buildHomeTeam(homeTeam)
										.buildAwayTeam(awayTeam)
										.buildTournament(tournament)
										.buildCountry(country)
										.build();
										
		
		DAOFactory factory = DAOFactory.getInstance();
		
		try {
			factory.getCompetitionDAO().addCompetition(competition);;
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}

	}

	@Override
	public void deleteCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String idCompetitionParam = requestContent.getRequestParam(PARAM_NAME_ID_COMPETITION);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdCompetition(idCompetitionParam);

		int idCompetition = Integer.valueOf(idCompetitionParam);
		
		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().deleteCompetition(idCompetition);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	@Override
	public void takeDataForCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String idSportParam = requestContent.getRequestParam(PARAM_NAME_ID_SPORT);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdSport(idSportParam);

		int idSport = Integer.valueOf(idSportParam);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			List<Tournament> tournamentList = factory.getTournamentDAO().obtainTournamentListByIdSport(idSport);
			List<Team> teamList = factory.getTeamDAO().obtainTeamListByIdSport(idSport);
			List<Country> countryList = factory.getCountryDAO().obtainCountryList();

			List<Object> attributeList = new ArrayList<>(3);

			attributeList.add(tournamentList);
			attributeList.add(teamList);
			attributeList.add(countryList);

			String json = new Gson().toJson(attributeList);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_JSON, json);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}

	}

	@Override
	public void obtainCompetitionList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String pageNumberParam = requestContent.getRequestParam(PARAM_NAME_PAGE_NUMBER);

		int pageNumber = 0;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			serviceFactory.getValidationService().validatePageNumber(pageNumberParam);
			pageNumber = Integer.valueOf(pageNumberParam);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (RECORDS_PER_PAGE);

		try {
			// TODO: пока нет постраничного запроса

			competitionList = factory.getCompetitionDAO().obtainCompetitionList(fromRecord, RECORDS_PER_PAGE);

			int availableCount = obtainAllCompetitionCount();
			int pageCount = SupportClass.calcPageCount(availableCount, RECORDS_PER_PAGE);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_COMPETITION_LIST, competitionList);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	private int obtainAllCompetitionCount() throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainCompetitionListCount();
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

	@Override
	public void obtainAvailableCompetitionsList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String pageNumberParam = requestContent.getRequestParam(PARAM_NAME_PAGE_NUMBER);

		int pageNumber = 0;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			serviceFactory.getValidationService().validatePageNumber(pageNumberParam);
			pageNumber = Integer.valueOf(pageNumberParam);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (RECORDS_PER_PAGE);

		try {
			competitionList = factory.getCompetitionDAO().obtainAvailableCompetitionList(fromRecord, RECORDS_PER_PAGE);

			int availableCount = obtainAvailableCompetitionsCount();
			int pageCount = SupportClass.calcPageCount(availableCount, RECORDS_PER_PAGE);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_COMPETITION_LIST, competitionList);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	private int obtainAvailableCompetitionsCount() throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainAvailableCompetitionListCount();
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

	@Override
	public void obtainSpecialCompetitionsList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String idSportParam = requestContent.getRequestParam(PARAM_NAME_ID_SPORT);
		String idTournamentParam = requestContent.getRequestParam(PARAM_NAME_ID_TOURNAMENT);
		String pageNumberParam = requestContent.getRequestParam(PARAM_NAME_PAGE_NUMBER);

		int pageNumber = 0;
		int idSport = 0;
		int idTournament = 0;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			serviceFactory.getValidationService().validatePageNumber(pageNumberParam);
			pageNumber = Integer.valueOf(pageNumberParam);
		}

		serviceFactory.getValidationService().validateIdSport(idSportParam);
		serviceFactory.getValidationService().validateIdTournament(idTournamentParam);
		idSport = Integer.valueOf(idSportParam);
		idTournament = Integer.valueOf(idTournamentParam);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * RECORDS_PER_PAGE;

		try {
			competitionList = factory.getCompetitionDAO().obtainSpecialCompetitionList(idSport, idTournament,
					fromRecord, RECORDS_PER_PAGE);

			int availableCount = obtainSpecialCompetitionsCount(idSport, idTournament);
			int pageCount = SupportClass.calcPageCount(availableCount, RECORDS_PER_PAGE);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_COMPETITION_LIST, competitionList);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	private int obtainSpecialCompetitionsCount(int idSport, int idTournament)
			throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainSpecialCompetitionListCount(idSport, idTournament);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

	@Override
	public void obtainCompetitionResultList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String pageNumberParam = requestContent.getRequestParam(PARAM_NAME_PAGE_NUMBER);

		int pageNumber = 0;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			serviceFactory.getValidationService().validatePageNumber(pageNumberParam);
			pageNumber = Integer.valueOf(pageNumberParam);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (RECORDS_PER_PAGE);

		try {
			competitionList = factory.getCompetitionDAO().obtainCompetitionResultList(fromRecord, RECORDS_PER_PAGE);

			int availableCount = obtainCompetitionResultCount();
			int pageCount = SupportClass.calcPageCount(availableCount, RECORDS_PER_PAGE);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_COMPETITION_LIST, competitionList);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	private int obtainCompetitionResultCount() throws ServiceException, ServiceValidationException {
		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainCompetitionResultListCount();
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}
}
