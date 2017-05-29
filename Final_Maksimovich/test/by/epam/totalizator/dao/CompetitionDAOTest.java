package by.epam.totalizator.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

public class CompetitionDAOTest {

	private ICompetitionDAO competitioDAO;
	private IConnectionPool pool;
	private static final int ID_COMPETITION = 15;
	private static final EventType RESULT = EventType.DRAW;

	@Before
	public void setUp() throws Exception {
		competitioDAO = DAOFactory.getInstance().getCompetitionDAO();
		pool = ConnectionPoolImpl.getInstance();
		pool.init();

	}

	@After
	public void tearDown() throws Exception {
		competitioDAO = null;
		pool.dispose();
		pool = null;
	}

	@Test
	public void testDeleteCompetition() throws DAOException {
		competitioDAO.deleteCompetition(ID_COMPETITION);
	}

	@Test
	public void testSetCompetitionResult() throws DAOException {
		competitioDAO.setCompetitionResult(ID_COMPETITION, RESULT);
	}
}