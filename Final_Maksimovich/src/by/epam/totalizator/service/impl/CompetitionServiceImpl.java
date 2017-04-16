package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.CompetitionService;
import by.epam.totalizator.service.ValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class CompetitionServiceImpl implements CompetitionService {
	
	private static final String COMPETITION_SERVICE_EXCEPTION_MESSAGE ="CompetitionService problem";

	@Override
	public void addCompetition(Competition competition) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateStartTime(competition.getStartTime());
		serviceFactory.getValidationService().validateIdSport(competition.getSport().getId());
		serviceFactory.getValidationService().validateIdClub(competition.getHomeTeam().getClub().getId());
		serviceFactory.getValidationService().validateIdClub(competition.getAwayTeam().getClub().getId());
		serviceFactory.getValidationService().validateIdTournament(competition.getTournament().getId());
		serviceFactory.getValidationService().validateIdCountry(competition.getCountry().getId());

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().addCompetition(competition);
			;
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}

	}

	@Override
	public Competition obtainCompetition(int idCompetition, Locale locale)
			throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdCompetition(idCompetition);

		DAOFactory factory = DAOFactory.getInstance();
		Competition competition = null;

		try {
			competition = factory.getCompetitionDAO().obtainCompetition(idCompetition, locale);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return competition;
	}

	@Override
	public void updateCompetition(Competition competition) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ValidationService validationService = serviceFactory.getValidationService();

		validationService.validateStartTime(competition.getStartTime());
		validationService.validateStartTime(competition.getStartTime());
		validationService.validateIdSport(competition.getSport().getId());
		validationService.validateIdClub(competition.getHomeTeam().getClub().getId());
		validationService.validateIdClub(competition.getAwayTeam().getClub().getId());
		validationService.validateIdTournament(competition.getTournament().getId());
		validationService.validateIdCountry(competition.getCountry().getId());

		EventType result = competition.getResult();

		DAOFactory factory = DAOFactory.getInstance();

		try {

			if (result != null) {
				int idCompetition = competition.getId();
				factory.getCompetitionDAO().setCompetitionResult(idCompetition, result);
			}

			factory.getCompetitionDAO().updateCompetition(competition);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void updateComeptititonRate(Competition competition) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ValidationService validationService = serviceFactory.getValidationService();

		validationService.validateIdSport(competition.getId());
		validationService.validateHomeRate(competition.getWinHomeRate());
		validationService.validateDrawRate(competition.getDrawRate());
		validationService.validateAwayRate(competition.getWinAwayRate());

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().updateAllCompetitionRate(competition);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void deleteCompetition(int idCompetition) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdCompetition(idCompetition);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().deleteCompetition(idCompetition);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public List<Competition> obtainCompetitionList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		List<Competition> competitionList;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();

		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			competitionList = factory.getCompetitionDAO().obtainCompetitionList(fromRecord, recordQuantityPerPage,
					locale);

		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return competitionList;
	}

	@Override
	public int obtainAllCompetitionCount() throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainCompetitionListCount();
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	@Override
	public List<Competition> obtainAvailableCompetitionList(boolean isClient, int pageNumber, int recordQuantityPerPage,
			Locale locale) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			competitionList = factory.getCompetitionDAO().obtainAvailableCompetitionList(fromRecord,
					recordQuantityPerPage, isClient, locale);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}

		return competitionList;
	}

	@Override
	public int obtainAvailableCompetitionListCount(boolean isClient)
			throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainAvailableCompetitionListCount(isClient);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	@Override
	public List<Competition> obtainSpecialCompetitionList(boolean isClient, int idSport, int idTournament,
			int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validatePageNumber(pageNumber);
		serviceFactory.getValidationService().validateIdSport(idSport);
		serviceFactory.getValidationService().validateIdTournament(idTournament);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * recordQuantityPerPage;

		try {
			competitionList = factory.getCompetitionDAO().obtainSpecialCompetitionList(idSport, idTournament,
					fromRecord, recordQuantityPerPage, isClient, locale);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competitionList;
	}

	@Override
	public int obtainSpecialCompetitionListCount(boolean isClient, int idSport, int idTournament)
			throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainSpecialCompetitionListCount(idSport, idTournament, isClient);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

	@Override
	public List<Competition> obtainCompetitionResultList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			competitionList = factory.getCompetitionDAO().obtainCompetitionResultList(fromRecord, recordQuantityPerPage,
					locale);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competitionList;
	}

	@Override
	public int obtainCompetitionResultListCount() throws ServiceException, ServiceValidationException {
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
