package by.epam.totalizator.dao.connection.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.dao.connection.ConnectionProvider;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.DBParameter;
import by.epam.totalizator.dao.util.DBResourceManager;

/**
 * #ConnectionProvider implementation for the custom pool connection provider
 */
public class ConnectionProviderImpl implements ConnectionProvider {

	private static final Logger LOGGER = LogManager.getLogger(ConnectionProviderImpl.class.getName());

	private IConnectionPool connectionPool;

	private static final int DEFAULT_POOL_SIZE = 5;

	public ConnectionProviderImpl() {
		connectionPool = ConnectionPoolImpl.getInstance();
		setPoolParam();
		initPool();
	}

	/**
	 * Method gets connection to a data source from {@link #connectionPool}
	 * 
	 * @return connection to database
	 * @throws SQLException
	 *             appears when #ConnectionPoolException occurred
	 */
	@Override
	public Connection getConnection() throws SQLException {

		try {
			return connectionPool.getConnection();
		} catch (ConnectionPoolException e) {
			LOGGER.log(Level.ERROR, e);
			throw new SQLException(e);
		}
	}

	/**
	 * Method releases {@link #connectionPool} resources
	 */
	@Override
	public void close() throws SQLException {
		connectionPool.dispose();
	}

	/**
	 * Method sets configuration parameters to {@link #connectionPool}
	 */
	private void setPoolParam() {

		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		connectionPool.setDriverName(dbResourceManager.getValue(DBParameter.DB_DRIVER));
		connectionPool.setUrl(dbResourceManager.getValue(DBParameter.DB_URL));
		connectionPool.setUser(dbResourceManager.getValue(DBParameter.DB_USER));
		connectionPool.setPassword(dbResourceManager.getValue(DBParameter.DB_PASSWORD));

		int poolSize;

		try {
			poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = DEFAULT_POOL_SIZE;
		}
		connectionPool.setPoolSize(poolSize);
	}

	/**
	 * Method for initializing {@link #connectionPool}
	 */
	private void initPool() {

		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
			LOGGER.log(Level.ERROR, e);
			throw new RuntimeException("Connection pool init error.", e);
		}
	}
}