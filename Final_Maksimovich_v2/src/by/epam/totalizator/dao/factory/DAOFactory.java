package by.epam.totalizator.dao.factory;

import by.epam.totalizator.dao.IBettingDAO;
import by.epam.totalizator.dao.ICompetitionDAO;
import by.epam.totalizator.dao.ICountryDAO;
import by.epam.totalizator.dao.ISportDAO;
import by.epam.totalizator.dao.ITeamDAO;
import by.epam.totalizator.dao.ITournamentDAO;
import by.epam.totalizator.dao.IUserDAO;
import by.epam.totalizator.dao.impl.BettingDAOImpl;
import by.epam.totalizator.dao.impl.CompetitionDAOImpl;
import by.epam.totalizator.dao.impl.CountryDAOImpl;
import by.epam.totalizator.dao.impl.SportDAOImpl;
import by.epam.totalizator.dao.impl.TeamDAOImpl;
import by.epam.totalizator.dao.impl.TournamentDAOImpl;
import by.epam.totalizator.dao.impl.UserDAOImpl;

/**
 * Factory pattern implementation for dao layer
 */
public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final IUserDAO userDAO = new UserDAOImpl();
	private final IBettingDAO bettingDAO = new BettingDAOImpl();
	private final ICompetitionDAO competitionDAO = new CompetitionDAOImpl();
	private final ICountryDAO countryDAO = new CountryDAOImpl();
	private final ITeamDAO teamDAO = new TeamDAOImpl();
	private final ITournamentDAO tournamentDAO = new TournamentDAOImpl();
	private final ISportDAO sportDAO = new SportDAOImpl();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public IBettingDAO getBettingDAO() {
		return bettingDAO;
	}

	public ICompetitionDAO getCompetitionDAO() {
		return competitionDAO;
	}

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public ITeamDAO getTeamDAO() {
		return teamDAO;
	}

	public ITournamentDAO getTournamentDAO() {
		return tournamentDAO;
	}

	public ISportDAO getSportDAO() {
		return sportDAO;
	}
}