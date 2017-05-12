package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the Competition entity uses
 * {@link Competition}
 */
public interface ICompetitionDAO {

	/**
	 * Method adds a new record to the competition table
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void addCompetition(Competition competition) throws DAOException;

	/**
	 * Method receives Competition entity by its id
	 * 
	 * @param idCompetition
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return instance of {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public Competition obtainCompetition(int idCompetition, Locale locale) throws DAOException;

	/**
	 * Method performs update of competition entity
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void updateCompetition(Competition competition) throws DAOException;

	/**
	 * Method performs update of the competition rates
	 * 
	 * @param competition
	 *            bean object {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void updateAllCompetitionRate(Competition competition) throws DAOException;

	/**
	 * Method performs update of the competition result
	 * 
	 * @param idCompetition
	 * @param result
	 *            instance of {@link EventType}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void setCompetitionResult(int idCompetition, EventType result) throws DAOException;

	/**
	 * Method performs deleting of the competition by its id
	 * 
	 * @param idCompetition
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void deleteCompetition(int idCompetition) throws DAOException;

	/**
	 * Method receives the total number of competition
	 * 
	 * @return number of entries
	 * @throws DAOException
	 *             if database error was detected
	 */
	public int obtainCompetitionListCount() throws DAOException;

	/**
	 * Method receives a list of competitions
	 * 
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordCount
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Competition> obtainCompetitionList(int fromRecord, int recordCount, Locale locale) throws DAOException;

	/**
	 * Method receives the total number of competitions without a specific
	 * result
	 * 
	 * @param isPrepared
	 *            A flag that indicates whether it is necessary to show the
	 *            prepared competitions for which you can bet
	 * 
	 * @return number of entries
	 * @throws DAOException
	 *             if database error was detected
	 */
	public int obtainAvailableCompetitionListCount(boolean isPrepared) throws DAOException;

	/**
	 * Method receives a list of competitions without a specific result
	 * 
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordCount
	 *            requested number of entries
	 * @param isPrepared
	 *            A flag that indicates whether it is necessary to show the
	 *            prepared competitions for which you can bet
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Competition> obtainAvailableCompetitionList(int fromRecord, int recordCount, boolean isPrepared,
			Locale locale) throws DAOException;

	/**
	 * Method receives the total number of competitions without a specific
	 * result with given idSport and idTournament
	 * 
	 * @param idSport
	 * @param idTournament
	 * @param isPrepared
	 *            A flag that indicates whether it is necessary to show the
	 *            prepared competitions for which you can bet
	 * 
	 * @return number of entries
	 * @throws DAOException
	 *             if database error was detected
	 */
	public int obtainSpecialCompetitionListCount(int idSport, int idTournament, boolean isPrepared) throws DAOException;

	/**
	 * Method receives a list of competitions without a specific result with
	 * given idSport and idTournament
	 * 
	 * @param idSport
	 * @param idTournament
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordCount
	 *            requested number of entries
	 * @param isPrepared
	 *            A flag that indicates whether it is necessary to show the
	 *            prepared competitions for which you can bet
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Competition> obtainSpecialCompetitionList(int idSport, int idTournament, int fromRecord,
			int recordCount, boolean isPrepared, Locale locale) throws DAOException;

	/**
	 * Method receives the total number of competitions with result (finished
	 * competitions)
	 * 
	 * @return number of entries
	 * @throws DAOException
	 *             if database error was detected
	 */
	public int obtainCompetitionResultListCount() throws DAOException;

	/**
	 * Method receives a list of competitions with result (finished
	 * competitions)
	 * 
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordCount
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link Competition}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Competition> obtainCompetitionResultList(int fromRecord, int recordCount, Locale locale)
			throws DAOException;
}
