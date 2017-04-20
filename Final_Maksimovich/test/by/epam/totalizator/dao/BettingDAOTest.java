package by.epam.totalizator.dao;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.build.BettingBuilder;
import by.epam.totalizator.bean.build.CompetitionBuilder;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

public class BettingDAOTest {

	private IBettingDAO bettingDao;
	private IConnectionPool pool;

	private static final int ID_COMPETITION = 1;
	private static final int ID_USER = 1;
	private static final EventType BET_TYPE = EventType.DRAW;
	private static final BigDecimal BET_SIZE = new BigDecimal(111.11);
	private static final BigDecimal BET_RATE = new BigDecimal(1.8);

	private Betting betting;

	@Before
	public void setUp() throws Exception {
		bettingDao = DAOFactory.getInstance().getBettingDAO();
		pool = ConnectionPoolImpl.getInstance();
		pool.init();

		betting = buildBetting();
	}

	@After
	public void tearDown() throws Exception {
		bettingDao = null;
		pool.dispose();
		pool = null;
	}

	@Test
	public void testMakeBetting() throws DAOException {
		bettingDao.makeBetting(betting);
	}

	private Betting buildBetting() {

		Competition competition = new CompetitionBuilder().buildId(ID_COMPETITION).build();
		User user = new UserBuilder().buildId(ID_USER).build();

		Betting betting = new BettingBuilder().buildCompetition(competition).buildUser(user).buildBetType(BET_TYPE)
				.buildBetSize(BET_SIZE).buildBetRate(BET_RATE).build();

		return betting;
	}

}
