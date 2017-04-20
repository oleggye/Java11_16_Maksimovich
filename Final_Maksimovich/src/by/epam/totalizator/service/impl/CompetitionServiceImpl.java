package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ICompetitionService;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * #ICompetitionService interface implementation for the Betting entity
 * {@link Competition}
 */
public class CompetitionServiceImpl implements ICompetitionService {

	private static final String COMPETITION_SERVICE_EXCEPTION_MESSAGE = "CompetitionService problem";

	/**
	 * Method checks the input parameters and performs a dao level call to add
	 * competition.
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void addCompetition(Competition competition) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateStartTime(competition.getStartTime());
		validationService.validateIdSport(competition.getSport().getId());
		validationService.validateIdClub(competition.getHomeTeam().getClub().getId());
		validationService.validateIdClub(competition.getAwayTeam().getClub().getId());
		validationService.validateIdTournament(competition.getTournament().getId());
		validationService.validateIdCountry(competition.getCountry().getId());

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().addCompetition(competition);
			;
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}

	}

	/**
	 * Method checks the input parameters and performs a dao level call to get
	 * competition.
	 * 
	 * @param idCompetition
	 * @param locale
	 *            for a localized query to the database
	 * @return instance of {@link Competition}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public Competition obtainCompetition(int idCompetition, Locale locale)
			throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdCompetition(idCompetition);

		DAOFactory factory = DAOFactory.getInstance();
		Competition competition = null;

		try {
			competition = factory.getCompetitionDAO()
					.obtainCompetition(idCompetition, locale);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return competition;
	}

	/**
	 * Method checks the input parameters and performs a dao level call to
	 * change competition.
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void updateCompetition(Competition competition) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
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

	/**
	 * Method checks the input parameters and performs a dao level call to
	 * change competition rates
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public void updateComeptititonRate(Competition competition) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
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

	/**
	 * Method checks the input parameters and performs a dao level call to
	 * remove a competition by its id
	 * 
	 * @param idCompetition
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public void deleteCompetition(int idCompetition) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdCompetition(idCompetition);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().deleteCompetition(idCompetition);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Method checks the input parameters and performs a dao level call to get a
	 * list of all competitions
	 * 
	 * @param pageNumber
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public List<Competition> obtainCompetitionList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		List<Competition> competitionList;

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();

		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			competitionList = factory.getCompetitionDAO()
					.obtainCompetitionList(fromRecord, recordQuantityPerPage, locale);

		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return competitionList;
	}

	/**
	 * Method performs a dao level call to get the total number of all
	 * competitions
	 * 
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public int obtainAllCompetitionCount() throws ServiceException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainCompetitionListCount();
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	/**
	 * Method checks the input parameters and performs a dao level call to get a
	 * list of competition without a specific result (available for betting)
	 * 
	 * @param isClient
	 *            a flag that indicates whether it is necessary to show the
	 *            prepared competitions (for which you can bet)
	 * @param pageNumber
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public List<Competition> obtainAvailableCompetitionList(boolean isClient, int pageNumber, int recordQuantityPerPage,
			Locale locale) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			competitionList = factory.getCompetitionDAO()
					.obtainAvailableCompetitionList(fromRecord,recordQuantityPerPage, isClient, locale);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}

		return competitionList;
	}

	/**
	 * Method performs a dao level call to get the total number of competition
	 * without a specific result (available for betting)
	 * 
	 * @param isClient
	 *            a flag that indicates whether it is necessary to show the
	 *            prepared competitions (for which you can bet)
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public int obtainAvailableCompetitionListCount(boolean isClient) throws ServiceException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		
		try {
			count = factory.getCompetitionDAO()
					.obtainAvailableCompetitionListCount(isClient);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	/**
	 * Method checks the input parameters and performs a dao level call to get a
	 * list of competition without a specific result with given idSport and
	 * idTournament
	 * 
	 * @param isClient
	 *            a flag that indicates whether it is necessary to show the
	 *            prepared competitions (for which you can bet)
	 * @param idSport
	 * @param idTournament
	 * @param pageNumber
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public List<Competition> obtainSpecialCompetitionList(boolean isClient, int idSport, int idTournament,
			int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validatePageNumber(pageNumber);
		validationService.validateIdSport(idSport);
		validationService.validateIdTournament(idTournament);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		int fromRecord = (pageNumber - 1) * recordQuantityPerPage;

		try {
			competitionList = factory.getCompetitionDAO()
					.obtainSpecialCompetitionList(idSport, idTournament, fromRecord, recordQuantityPerPage, isClient, locale);

		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return competitionList;
	}

	/**
	 * Method performs a dao level call to get the total number of competition
	 * without a specific result with given idSport and idTournament
	 * 
	 * @param isClient
	 *            a flag that indicates whether it is necessary to show the
	 *            prepared competitions (for which you can bet)
	 * @param idSport
	 * @param idTournament
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public int obtainSpecialCompetitionListCount(boolean isClient, int idSport, int idTournament)
			throws ServiceException, ServiceValidationException {

		int count;

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdSport(idSport);
		validationService.validateIdTournament(idTournament);

		DAOFactory factory = DAOFactory.getInstance();
		
		try {
			count = factory.getCompetitionDAO().obtainSpecialCompetitionListCount(idSport, idTournament, isClient);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	/**
	 * Method checks the input parameters and performs a dao level call to get a
	 * list of competitions with result (finished competitions)
	 * 
	 * @param pageNumber
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	@Override
	public List<Competition> obtainCompetitionResultList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitionList;
		
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			competitionList = factory.getCompetitionDAO()
					.obtainCompetitionResultList(fromRecord, recordQuantityPerPage, locale);
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return competitionList;
	}

	/**
	 * Method checks the input parameters and performs a dao level call to get a
	 * list of competitions with result (finished competitions)
	 * 
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 */
	@Override
	public int obtainCompetitionResultListCount() throws ServiceException {
		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().obtainCompetitionResultListCount();
		} catch (DAOException e) {
			throw new ServiceException(COMPETITION_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}
}
