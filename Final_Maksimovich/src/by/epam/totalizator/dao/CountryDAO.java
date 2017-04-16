package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the Country entity uses
 * {@link Country}
 */
public interface CountryDAO {

	/**
	 * Method receives a list of countries
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Country}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<Country> obtainCountryList(Locale locale) throws DAOException;

}
