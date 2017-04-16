package by.epam.totalizator.service.impl;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.TournamentService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class TournamentServiceImpl implements TournamentService {

	private static final String TOURNAMENT_SERVICE_EXCEPTION_MESSAGE = "TournamentService problem";

	@Override
	public List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale)
			throws ServiceException, ServiceValidationException {

		List<Tournament> tournamentList = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdSport(idSport);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			tournamentList = factory.getTournamentDAO().obtainTournamentListByIdSport(idSport, locale);

		} catch (DAOException e) {
			throw new ServiceException(TOURNAMENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return tournamentList;
	}
}
