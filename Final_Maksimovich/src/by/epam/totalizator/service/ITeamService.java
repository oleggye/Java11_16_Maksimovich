package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Service interface for the Team entity {@link Team}
 */
public interface ITeamService {

	/**
	 * Method performs a dao level call to get a list of all teams {@link Team}
	 * by sport id {@link Sport}
	 * 
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Team}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	List<Team> obtainTeamListByIdSport(int idSport, Locale locale) throws ServiceException, ServiceValidationException;
}