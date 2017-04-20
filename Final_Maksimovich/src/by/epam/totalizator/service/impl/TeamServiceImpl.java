package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ITeamService;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * #ITeamService interface implementation for the Sport entity {@link Team}
 */
public class TeamServiceImpl implements ITeamService {

	private static final String TEAM_SERVICE_EXCEPTION_MESSAGE = "TeamService problem";

	/**
	 * Method performs a dao level call to get a list of all teams {@link Team}
	 * by sport id {@link by.epam.totalizator.bean.Sport}
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Team}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public List<Team> obtainTeamListByIdSport(int idSport, Locale locale)
			throws ServiceException, ServiceValidationException {

		List<Team> teamList = null;
		
		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdSport(idSport);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			teamList = factory.getTeamDAO().obtainTeamListByIdSport(idSport, locale);

		} catch (DAOException e) {
			throw new ServiceException(TEAM_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return teamList;
	}
}