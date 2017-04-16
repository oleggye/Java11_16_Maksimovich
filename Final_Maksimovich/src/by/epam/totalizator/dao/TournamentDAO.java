package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the Tournament entity uses
 * {@link Tournament}
 */
public interface TournamentDAO {

	/**
	 * Method receives a list of tournaments by the sport
	 * 
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Tournament}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale) throws DAOException;
}
