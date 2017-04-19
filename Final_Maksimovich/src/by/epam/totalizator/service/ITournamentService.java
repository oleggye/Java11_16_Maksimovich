package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Service interface for the Tournament entity {@link Tournament}
 */
public interface ITournamentService {

	/**
	 * Method performs a dao level call to get a list of all tournaments
	 * {@link Tournament} by sport id {@link Sport}
	 * 
	 * @param idSport
	 * @param locale
	 * @return
	 * @throws ServiceException
	 * @throws ServiceValidationException
	 */
	List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale)
			throws ServiceException, ServiceValidationException;
}