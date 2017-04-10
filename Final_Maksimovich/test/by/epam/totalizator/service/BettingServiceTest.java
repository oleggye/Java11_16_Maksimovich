package by.epam.totalizator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.impl.BettingServiceImpl;

public class BettingServiceTest {

	private BettingService bettingService;
	private IConnectionPool pool;

	private static final EventType result = EventType.DRAW;
	private static final int idCompetition = 15;

	@Before
	public void setUp() throws Exception {
		bettingService = new BettingServiceImpl();
		pool = ConnectionPoolImpl.getInstance();

		pool.init();
	}

	@After
	public void tearDown() throws Exception {
		bettingService = null;
		pool.dispose();
		pool = null;
	}

	@Test
	public void testEnrollAWin() throws ServiceException, ServiceValidationException {
		bettingService.enrollAWin(idCompetition, result);
	}

}
