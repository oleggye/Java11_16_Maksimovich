package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Service interface for the Betting entity {@link Competition}
 */
public interface ICompetitionService {

	/**
	 * Method checks the input parameters and performs a dao level call to add
	 * competition.
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void addCompetition(Competition competition) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters and performs a dao level call to get
	 * competition.
	 * 
	 * @param idCompetition
	 * @param locale
	 *            for a localized query to the database
	 * @return instance of {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public Competition obtainCompetition(int idCompetition, Locale locale)
			throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters and performs a dao level call to
	 * change competition.
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void updateCompetition(Competition competition) throws ServiceException, ServiceValidationException;

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
	public void updateComeptititonRate(Competition competition) throws ServiceException, ServiceValidationException;

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
	public void deleteCompetition(int idCompetition) throws ServiceException, ServiceValidationException;

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
	public List<Competition> obtainCompetitionList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

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
	public int obtainAllCompetitionCount() throws ServiceException;

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
	public List<Competition> obtainAvailableCompetitionList(boolean isClient, int pageNumber, int recordQuantityPerPage,
			Locale locale) throws ServiceException, ServiceValidationException;

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
	public int obtainAvailableCompetitionListCount(boolean isClient) throws ServiceException;

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
	public List<Competition> obtainSpecialCompetitionList(boolean isClient, int idSport, int idTournament,
			int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

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
	public int obtainSpecialCompetitionListCount(boolean isClient, int idSport, int idTournament)
			throws ServiceException, ServiceValidationException;

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
	public List<Competition> obtainCompetitionResultList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters and performs a dao level call to get a
	 * list of competitions with result (finished competitions)
	 * 
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 */
	public int obtainCompetitionResultListCount() throws ServiceException;
}
