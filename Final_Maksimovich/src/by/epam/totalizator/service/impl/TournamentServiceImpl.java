package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ITournamentService;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * #ITournamentService interface implementation for the Sport entity
 * {@link Sport}
 */
public class TournamentServiceImpl implements ITournamentService {

	private static final String TOURNAMENT_SERVICE_EXCEPTION_MESSAGE = "TournamentService problem";

	/**
	 * Method performs a dao level call to get a list of all tournaments
	 * {@link Tournament} by sport id {@link Sport}
	 * 
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Tournament}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale)
			throws ServiceException, ServiceValidationException {

		List<Tournament> tournamentList = null;

		IValidationService validationService = ServiceFactory.getInstance().getValidationService();
		validationService.validateIdSport(idSport);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			tournamentList = factory.getTournamentDAO().obtainTournamentListByIdSport(idSport, locale);

		} catch (DAOException e) {
			throw new ServiceException(TOURNAMENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return tournamentList;
	}
}