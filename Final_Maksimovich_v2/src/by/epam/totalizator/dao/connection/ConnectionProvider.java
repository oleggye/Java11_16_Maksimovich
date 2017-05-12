package by.epam.totalizator.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for a provider of connections
 */
public interface ConnectionProvider {

	/**
	 * Method gets connection to a data source
	 * 
	 * @return connection to a data source
	 * @throws SQLException
	 *             when problem with obtaining a connection occurred
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * Method for releasing opened resources
	 * 
	 * @throws SQLException
	 *             when problem with resources releasing occurred
	 */
	public void close() throws SQLException;
}