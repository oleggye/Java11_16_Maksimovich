package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.service.exception.ServiceException;

/**
 * Service interface for the Country entity {@link Country}
 */
public interface ICountryService {

	/**
	 * Method performs a dao level call to get a list of all countries
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Country}
	 * @throws ServiceException
	 *             if dao error was detected
	 */
	List<Country> obtainCountryList(Locale locale) throws ServiceException;
}
