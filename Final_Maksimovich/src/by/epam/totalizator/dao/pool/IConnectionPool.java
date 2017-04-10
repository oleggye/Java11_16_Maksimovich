package by.epam.totalizator.dao.pool;

import java.sql.Connection;

import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;

/**
 * Interface for custom simple Connection pool
 */
public interface IConnectionPool {
	/**
	 * Initialize database driver, connect to database, create connections
	 * Method execute in listener
	 * {@link by.epam.task6.listener.ConnectionProviderListener}
	 * 
	 * @throws ConnectionPoolException
	 *             appears when can't connect to database or initialize database
	 *             driver
	 */
	void init() throws ConnectionPoolException;

	/**
	 * Dispose all created database connections
	 */
	void dispose();

	/**
	 * Get free connection from connection's queue
	 * 
	 * @return DataBase connection
	 * @throws ConnectionPoolException
	 *             appears when can't get free connection
	 */
	Connection getConnection() throws ConnectionPoolException;
}