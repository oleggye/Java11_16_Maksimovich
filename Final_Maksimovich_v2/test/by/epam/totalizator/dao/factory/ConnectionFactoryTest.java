package by.epam.totalizator.dao.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.dao.connection.ConnectionProvider;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

public class ConnectionFactoryTest {

	private static final int SIZE = 10000;
	private static final SQLName SQL_NAME = SQLName.GET_COUNTRY_LIST_EN;

	private ConnectionProvider factory;

	@Before
	public void setUp() throws Exception {
		factory = ConnectionFactory.getInstance().getConnectionProvider();

	}

	@After
	public void tearDown() throws Exception {
		factory.close();
		factory = null;
	}

	@Test
	public void test() throws InterruptedException {
		
		long startPoint = System.nanoTime();
		
		Thread[] arr = new Thread[SIZE];

		for (int i = 0; i < SIZE; i++) {
			
			arr[i] = new Thread() {
				@Override
				public void run() {
					try (Connection connection = factory.getConnection();
							PreparedStatement statement = connection
									.prepareStatement(SQLProvider.getInstance().getSql(SQL_NAME));

							ResultSet result = statement.executeQuery()) {

					} catch (SQLException e) {
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
		double resultTime_ms = ((endPoint - startPoint)/1000000);
		
		System.out.println("Result: " + resultTime_ms + " ms");
	}
}