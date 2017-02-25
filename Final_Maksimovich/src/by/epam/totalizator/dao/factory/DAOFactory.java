package by.epam.totalizator.dao.factory;

import by.epam.totalizator.dao.BettingDAO;
import by.epam.totalizator.dao.CompetitionDAO;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.impl.BettingDAOImpl;
import by.epam.totalizator.dao.impl.CompetitionDAOImpl;
import by.epam.totalizator.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO userDAO = new UserDAOImpl();
	private final BettingDAO bettingDAO = new BettingDAOImpl();
	private final CompetitionDAO competitionDAO = new CompetitionDAOImpl();

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

}
