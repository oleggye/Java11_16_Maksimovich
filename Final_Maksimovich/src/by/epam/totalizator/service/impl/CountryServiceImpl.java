package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ICountryService;
import by.epam.totalizator.service.exception.ServiceException;

/**
 * #ICountryService interface implementation for the Country entity
 * {@link Country}
 */
public class CountryServiceImpl implements ICountryService {

	private static final String COUNTRY_SERVICE_EXCEPTION_MESSAGE = "CountryService problem";

	/**
	 * Method performs a dao level call to get a list of all countries
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Country}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 */
	@Override
	public List<Country> obtainCountryList(Locale locale) throws ServiceException {

		List<Country> countryList = null;

		DAOFactory factory = DAOFactory.getInstance();

		try {
			countryList = factory.getCountryDAO().obtainCountryList(locale);

		} catch (DAOException e) {
			throw new ServiceException(COUNTRY_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return countryList;
	}
}