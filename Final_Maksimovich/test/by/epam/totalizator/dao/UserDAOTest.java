package by.epam.totalizator.dao;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

public class UserDAOTest {

	private UserDAO userDao;
	private IConnectionPool pool;

	private static final int ID_USER = 1;
	private static final BigDecimal AMOUNT = new BigDecimal(-1);

	@Before
	public void setUp() throws Exception {
		userDao = DAOFactory.getInstance().getUserDAO();
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
		userDao.changeUserBalance(ID_USER, AMOUNT);
	}
}
