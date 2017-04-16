package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.SportService;
import by.epam.totalizator.service.exception.ServiceException;

public class SportServiceImpl implements SportService {

	private static final String SPORT_SERVICE_EXCEPTION_MESSAGE = "SportService problem";

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
