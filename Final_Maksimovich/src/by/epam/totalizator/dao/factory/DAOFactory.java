package by.epam.totalizator.dao.factory;

import by.epam.totalizator.dao.BettingDAO;
import by.epam.totalizator.dao.CompetitionDAO;
import by.epam.totalizator.dao.CountryDAO;
import by.epam.totalizator.dao.TeamDAO;
import by.epam.totalizator.dao.TournamentDAO;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.impl.BettingDAOImpl;
import by.epam.totalizator.dao.impl.CompetitionDAOImpl;
import by.epam.totalizator.dao.impl.CountryDAOImpl;
import by.epam.totalizator.dao.impl.TeamDAOImpl;
import by.epam.totalizator.dao.impl.TournamentDAOImpl;
import by.epam.totalizator.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO userDAO = new UserDAOImpl();
	private final BettingDAO bettingDAO = new BettingDAOImpl();
	private final CompetitionDAO competitionDAO = new CompetitionDAOImpl();
	private final CountryDAO countryDAO = new CountryDAOImpl();
	private final TeamDAO teamDAO = new TeamDAOImpl();
	private final TournamentDAO tournamentDAO = new TournamentDAOImpl();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BettingDAO getBettingDAO() {
		return bettingDAO;
	}

	public CompetitionDAO getCompetitionDAO() {
		return competitionDAO;
	}

	public CountryDAO getCountryDAO() {
		return countryDAO;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public TournamentDAO getTournamentDAO() {
		return tournamentDAO;
	}

}
