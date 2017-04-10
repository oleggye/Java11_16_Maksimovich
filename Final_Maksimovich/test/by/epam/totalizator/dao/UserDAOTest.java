package by.epam.totalizator.dao;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.impl.UserDAOImpl;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

public class UserDAOTest {

	private UserDAO userDao;
	private IConnectionPool pool;

	private static final int idUser = 1;
	private static final BigDecimal amount = new BigDecimal(-1);

	@Before
	public void setUp() throws Exception {
		userDao = new UserDAOImpl();
		pool = ConnectionPoolImpl.getInstance();
		pool.init();
	}

	@After
	public void tearDown() throws Exception {
		userDao = null;
		pool.dispose();
		pool = null;
	}

	@Test
	public void testIncreaseUserBalance() throws DAOException {
		userDao.changeUserBalance(idUser, amount);
	}
}
