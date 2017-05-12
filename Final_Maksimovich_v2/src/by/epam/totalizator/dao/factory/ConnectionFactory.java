package by.epam.totalizator.dao.factory;

import by.epam.totalizator.dao.connection.ConnectionProvider;
import by.epam.totalizator.dao.connection.impl.ConnectionProviderImpl;

/**
 * Factory pattern implementation for creating connection providers
 */
public class ConnectionFactory {

	private static final ConnectionFactory INSTANCE = new ConnectionFactory();

	private final ConnectionProvider connectionFactory = new ConnectionProviderImpl();

	private ConnectionFactory() {}

	public static ConnectionFactory getInstance() {
		return INSTANCE;
	}

	public ConnectionProvider getConnectionProvider() {
		return connectionFactory;
	}
}