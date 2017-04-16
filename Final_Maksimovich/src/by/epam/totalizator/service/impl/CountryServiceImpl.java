package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.CountryService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public class CountryServiceImpl implements CountryService {

	private static final String COUNTRY_SERVICE_EXCEPTION_MESSAGE = "CountryService problem";

	@Override
	public List<Country> obtainCountryList(Locale locale) throws ServiceException, ServiceValidationException {

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
