package by.tc.eq.dao.util;

import java.sql.Connection;

import by.tc.eq.dao.exeption.DAOException;

public class ConnectionFactory {

	private static ConnectionPool connectionPool = null;

	public static Connection getConnection() throws DAOException {

		Connection connection = null;

		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
			try {
				connectionPool.initPoolData();

			} catch (ConnectionPoolException e) {
				throw new DAOException("Error while pool initialization", e);
			}
		}

		try {
			connection = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error while taking pool connection", e);
		}

		return connection;
	}

	public static void closeConnetion(Connection connection) throws DAOException {

		if (connectionPool == null) {
			throw new DAOException("Connection pool isn't initialized");
		} else {

			try {
				connectionPool.putbackConnection(connection);
			} catch (ConnectionPoolException e) {
				throw new DAOException("Error while putting back pool connection", e);

			}
		}
	}
}
