package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface CountryService {

	List<Country> obtainCountryList(Locale locale) throws ServiceException, ServiceValidationException;
}
