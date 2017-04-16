package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the Betting entity uses
 * {@link Betting}
 */
public interface BettingDAO {

	/**
	 * Method adds a new record to the betting table
	 * 
	 * @param betting
	 *            bean object {@link Betting}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void makeBetting(Betting betting) throws DAOException;;

	/**
	 * Method receives a list of betting of the user by its id
	 * 
	 * @param userId
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordCount
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link Betting}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Betting> obtainUserBettingList(int userId, int fromRecord, int recordCount, Locale locale)
			throws DAOException;

	/**
	 * Method receives the total number of betting of the user by its id
	 * 
	 * @param IdUser
	 * @return number of entries
	 * @throws DAOException
	 *             if database error was detected
	 */
	public int obtainUserBettingCount(int IdUser) throws DAOException;

	/**
	 * Method receives a list of betting of the competition by its id
	 * 
	 * @param idCompetition
	 * @return {@link java.util.List} instances of {@link Betting}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Betting> obtainBettingListForComeptition(int idCompetition) throws DAOException;
}
