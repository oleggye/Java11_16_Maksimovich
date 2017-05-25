package by.epam.totalizator.dao.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

public class ConnectionPoolTest {

	private IConnectionPool pool;
	private static final int SIZE = 10000;
	private static final SQLName SQL_NAME = SQLName.GET_COUNTRY_LIST_EN;

	@Before
	public void setUp() throws Exception {

		pool = ConnectionPoolImpl.getInstance();
		try {
			pool.init();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		pool.dispose();
		pool = null;
	}

	@Test
	public void testConnectionPool() throws InterruptedException {

		long startPoint = System.nanoTime();
		
		Thread[] arr = new Thread[SIZE];

		for (int i = 0; i < SIZE; i++) {
			arr[i] = new Thread() {
				@Override
				public void run() {
					try (Connection connection = pool.getConnection();
							PreparedStatement statement = connection
									.prepareStatement(
											SQLProvider.getInstance().getSql(SQL_NAME));

							ResultSet result = statement.executeQuery()) {

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ConnectionPoolException e) {
						e.printStackTrace();
					}
				}
			};
			arr[i].start();
		}

		for (int i = 0; i < SIZE; i++) {
			arr[i].join();
		}
		
		long endPoint = System.nanoTime();
		double resultTime_ms = (endPoint - startPoint)/1000000;
		
		System.out.println("Result: " + resultTime_ms + " ms");
	}
}