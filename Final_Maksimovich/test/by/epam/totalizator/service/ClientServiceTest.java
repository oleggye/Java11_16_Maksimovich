package by.epam.totalizator.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.impl.ClientServiceImpl;

public class ClientServiceTest {

	private IClientService clientService;
	private IConnectionPool pool;

	private static final int pageNumber = 1;
	private static final int recordQuantityPerPage = 5;

	private static final Locale LOCALE = new Locale("ru");

	@Before
	public void setUp() throws Exception {
		clientService = new ClientServiceImpl();
		pool = ConnectionPoolImpl.getInstance();

		pool.init();
	}

	@After
	public void tearDown() throws Exception {
		clientService = null;
		pool.dispose();
		pool = null;
	}

	@Test
	public void testObtainUserList() throws ServiceException, ServiceValidationException {
		assertFalse(executeFucntion().isEmpty());
	}

	private List<User> executeFucntion() throws ServiceException, ServiceValidationException {
		return clientService.obtainUserList(pageNumber, recordQuantityPerPage, LOCALE);
	}
}