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
import by.epam.totalizator.dao.impl.BettingDAOImpl;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

public class BettingDAOTest {

	private BettingDAO bettingDao;
	private IConnectionPool pool;

	private static final int idBetting = 14;
	private static final BigDecimal gain = new BigDecimal(11042.64);

	private static final int idCompetition = 1;
	private static final int idUser = 1;
	private static final EventType betType = EventType.DRAW;
	private static final BigDecimal betSize = new BigDecimal(111.11);
	private static final BigDecimal betRate = new BigDecimal(1.8);

	private Betting betting;

	@Before
	public void setUp() throws Exception {
		bettingDao = new BettingDAOImpl();
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
	public void testDefineBettingGain() throws DAOException {
		bettingDao.defineBettingGain(idBetting, gain);
	}

//	@Test
//	public void testMakeBetting() throws DAOException {
//		bettingDao.makeBetting(betting);
//	}

	private Betting buildBetting() {

		Competition competition = new CompetitionBuilder().buildId(idCompetition).build();
		User user = new UserBuilder().buildId(idUser).build();

		Betting betting = new BettingBuilder().buildCompetition(competition).buildUser(user).buildBetType(betType)
				.buildBetSize(betSize).buildBetRate(betRate).build();

		return betting;
	}

}
