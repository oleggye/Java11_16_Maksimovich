package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.service.exception.ServiceException;

/**
 * Service interface for the Sport entity {@link Sport}
 */
public interface ISportService {

	/**
	 * Method performs a dao level call to get a list of all sports
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Sport}
	 * @throws ServiceException
	 *             if dao error was detected
	 */
	List<Sport> obtainSportList(Locale locale) throws ServiceException;
}
