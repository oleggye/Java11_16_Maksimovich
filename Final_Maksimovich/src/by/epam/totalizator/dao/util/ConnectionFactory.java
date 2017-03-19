package by.epam.totalizator.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	private static final ConnectionFactory instance = new ConnectionFactory();

	private boolean isDataSourceInitialized = false;

	private DataSource dataSource;

	private ConnectionFactory() {
		try {
			Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
			dataSource = (DataSource) envCtx.lookup("jdbc/totalizator");

			isDataSourceInitialized = true;
		} catch (NamingException e) {
			// TODO logging!!!!!
		}
	}

	public static ConnectionFactory getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {

		if (isDataSourceInitialized) {

			return dataSource.getConnection();
		} else {
			throw new SQLException("DataSource isn't initialized");
		}
	}
}
