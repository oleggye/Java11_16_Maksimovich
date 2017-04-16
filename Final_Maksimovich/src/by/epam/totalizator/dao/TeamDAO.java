package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the Team entity uses {@link Team}
 */
public interface TeamDAO {

	/**
	 * Method receives a list of teams by the sport
	 * 
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Team}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Team> obtainTeamListByIdSport(int idSport, Locale locale) throws DAOException;
}
