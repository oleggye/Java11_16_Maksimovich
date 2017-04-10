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

	private ClientService clientService;
	private IConnectionPool pool;

	int pageNumber;
	int recordQuantityPerPage;
	private static final Locale locale = new Locale("ru");

	@Before
	public void setUp() throws Exception {
		clientService = new ClientServiceImpl();
		pool = ConnectionPoolImpl.getInstance();

		pool.init();

		pageNumber = 1;
		recordQuantityPerPage = 5;
	}

	@After
	public void tearDown() throws Exception {
		clientService = null;
		pool.dispose();
		pool = null;
	}

	@Test
	public void testObtainUserList() throws ServiceException, ServiceValidationException {
		for (int i = 0; i < 10; i++) {
			assertFalse(executeFucntion().isEmpty());
		}
	}

	private List<User> executeFucntion() throws ServiceException, ServiceValidationException {
		return clientService.obtainUserList(pageNumber, recordQuantityPerPage, locale);
	}
}
