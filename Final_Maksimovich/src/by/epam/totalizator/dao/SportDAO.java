package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the Sport entity uses {@link Sport}
 */
public interface SportDAO {

	/**
	 * Method receives a list of sports
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Sport}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Sport> obtainSportList(Locale locale) throws DAOException;
}
