package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.TeamService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class TeamServiceImpl implements TeamService {

	@Override
	public List<Team> obtainTeamListByIdSport(int idSport, Locale locale)
			throws ServiceException, ServiceValidationException {

		List<Team> teamList = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdSport(idSport);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			teamList = factory.getTeamDAO().obtainTeamListByIdSport(idSport, locale);

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return teamList;
	}

}
