package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ISportService;
import by.epam.totalizator.service.exception.ServiceException;

/**
 * #ISportService interface implementation for the Sport entity {@link Sport}
 */
public class SportServiceImpl implements ISportService {

	private static final String SPORT_SERVICE_EXCEPTION_MESSAGE = "SportService problem";

	/**
	 * Method performs a dao level call to get a list of all sports
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Sport}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 */
	@Override
	public List<Sport> obtainSportList(Locale locale) throws ServiceException {

		List<Sport> sportList = null;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			sportList = factory.getSportDAO().obtainSportList(locale);

		} catch (DAOException e) {
			throw new ServiceException(SPORT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return sportList;
	}
}