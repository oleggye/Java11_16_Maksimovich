package by.epam.totalizator.service.impl;

import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.CompetitionService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;
import by.epam.totalizator.service.util.SupportClass;

public class CompetitionServiceImpl implements CompetitionService {

	private static final String PARAM_NAME_PAGE_NUMBER = "pageNumber";
	private static final String PARAM_NAME_ID_SPORT = "idsport";
	private static final String PARAM_NAME_ID_TOURNAMENT = "idtourn";
	private static final int RECORDS_PER_PAGE = 6;
	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final String ATTRIBUTE_NAME_COMPETITION_LIST = "competitionList";
	private static final String ATTRIBUTE_NAME_PAGE_COUNT = "count";
	private static final String ATTRIBUTE_NAME_PAGE_NUMBER = "pageNumber";

	@Override
	public void addCompetition(Competition competition) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public Competition obtainCompetition(int id) throws ServiceException, ServiceValidationException {

		if (id < 1) {
			throw new ServiceException("Wrong param: id= " + id);
		}

		DAOFactory factory = DAOFactory.getInstance();
		Competition competition = null;

		try {
			competition = factory.getCompetitionDAO().obtainCompetition(id);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competition;
	}

	@Override
	public void updateCompetition(Competition competition) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCompetition(int id) throws ServiceException, ServiceValidationException {

		if (id < 1) {
			throw new ServiceException("Wrong param: id= " + id);
		}

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().deleteCompetition(id);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}

	}

	@Override
	public void obtainCompetitionsList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		String pageNumberParam = requestContent.getRequestParam(PARAM_NAME_PAGE_NUMBER);

		int pageNumber = 0;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			serviceFactory.getValidationService().validatePageNumber(pageNumberParam);
			pageNumber = Integer.parseInt(pageNumberParam);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (RECORDS_PER_PAGE);

		try {
			// TODO: пока нет постраничного запроса

			competitionList = factory.getCompetitionDAO().obtainCompetitionsList();

			int availableCount = obtainAllCompetitionsCount();
			int pageCount = SupportClass.calcPageCount(availableCount, RECORDS_PER_PAGE);

			requestContent.putRequestAttribute(ATTRIBUTE_NAME_COMPETITION_LIST, competitionList);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

	private int obtainAllCompetitionsCount() throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub
		return 0;
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
			pageNumber = Integer.parseInt(pageNumberParam);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (RECORDS_PER_PAGE);

		try {
			competitionList = factory.getCompetitionDAO().obtainAvailableCompetitionsList(fromRecord, RECORDS_PER_PAGE);

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
			count = factory.getCompetitionDAO().obtainAvailableCompetitionsCount();
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
			pageNumber = Integer.parseInt(pageNumberParam);
		}

		serviceFactory.getValidationService().validateIdSportAndIdTournament(idSportParam, idTournamentParam);
		idSport = Integer.parseInt(idSportParam);
		idTournament = Integer.parseInt(idTournamentParam);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * RECORDS_PER_PAGE;

		try {
			competitionList = factory.getCompetitionDAO().obtainSpecialCompetitionsList(idSport, idTournament, fromRecord,
					RECORDS_PER_PAGE);

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
			count = factory.getCompetitionDAO().obtainSpecialCompetitionsCount(idSport, idTournament);
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
			pageNumber = Integer.parseInt(pageNumberParam);
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
			count = factory.getCompetitionDAO().obtainCompetitionResultCount();
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}
}
