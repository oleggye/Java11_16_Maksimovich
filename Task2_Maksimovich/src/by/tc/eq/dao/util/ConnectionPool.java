package by.tc.eq.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

	private static final int DEFAULT_POOL_SIZE = 5;

	private BlockingQueue<Connection> avalibleConnectionsQueue;
	private BlockingQueue<Connection> reservedConnectionsQueue;

	private String driverName;
	private String url;
	private String login;
	private String password;
	private int poolSize;

	public ConnectionPool() {

		DBResourceManager dbResourseManager = DBResourceManager.getInstance();

		this.driverName = dbResourseManager.getValue(ConnectionParameter.DB_DRIVER);
		this.url = dbResourseManager.getValue(ConnectionParameter.DB_URL);
		this.login = dbResourseManager.getValue(ConnectionParameter.DB_USER);
		this.password = dbResourseManager.getValue(ConnectionParameter.DB_PASSWORD);

		try {
			this.poolSize = Integer.parseInt(dbResourseManager.getValue(ConnectionParameter.DB_POOL_SIZE));

		} catch (NumberFormatException e) {
			poolSize = DEFAULT_POOL_SIZE;
		}

	}

	public void initPoolData() throws ConnectionPoolException {

		try {

			Class.forName(driverName);

			avalibleConnectionsQueue = new ArrayBlockingQueue<>(poolSize);
			reservedConnectionsQueue = new ArrayBlockingQueue<>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, login, password);
				avalibleConnectionsQueue.put(connection);
			}

		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can not find database driver: driverName: '" + driverName + "'", e);

		} catch (SQLException e) {
			throw new ConnectionPoolException("An error occured in trying to establish connection:"
					+ " connection data: url: '" + url + "', login: '" + login + "', '" + password + "'", e);

		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Can not initPoolData", e);
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {

		Connection connection = null;

		if (avalibleConnectionsQueue.size() > 0) {

			try {

				connection = avalibleConnectionsQueue.take();
				reservedConnectionsQueue.add(connection);
				return connection;

			} catch (InterruptedException e) {
				throw new ConnectionPoolException("Errow connection to the data source", e);
			}
		}
		return connection;

	}

	public synchronized void putbackConnection(Connection connection) throws ConnectionPoolException {

		try {
			if (connection != null) {
				reservedConnectionsQueue.remove(connection);
				avalibleConnectionsQueue.add(connection);
			} else {
				Exception e = new NullPointerException();
				throw new ConnectionPoolException("connection is null", e);
			}
			// т.к. методы remove и add могут вызвать 6 исключений, то решено
			// перехватывать их предка и возвращать общуюю проблему
		} catch (Exception e) {
			throw new ConnectionPoolException("Problems with putting back occured", e);
		}
	}

	public void closeConnectionPool() {

		/**
		 * т.к. при попытке закрытия соединений в каждой из очередей может
		 * возникнуть SQLException, то решено раздедилить выполение метомда
		 * closeConnectionsQueue на два блока try-catch
		 */

		try {
			closeConnectionsQueue(avalibleConnectionsQueue);
		} catch (SQLException e) {
			// TODO: logging
		}

		try {
			closeConnectionsQueue(reservedConnectionsQueue);
		} catch (SQLException e) {
			// TODO: logging
		}

	}

	private void closeConnectionsQueue(BlockingQueue<Connection> connectionQueue) throws SQLException {

		/**
		 * Данный метод должен попытаться принудительно закрыть все соединения в
		 * очереди, причем, т.к. каждый элемент очереди может выкинуть
		 * исключение, то необходимо каждое исключение добавлять к имеющимуся и
		 * продолжать закрывать остальные, и , если исключения имели место быть,
		 * то выбросить их из метода
		 */

		SQLException exception = null;

		for (Connection connection : connectionQueue) {

			try {

				if (connection != null) {
					if (!connection.getAutoCommit()) {

						connection.commit();
					}
					connection.close();
				}

			} catch (SQLException e) {
				if (exception == null) {
					exception = e;
				} else {
					exception.addSuppressed(e);
				}
			}
		}
		if (exception != null) {
			throw exception;
		}
	}

	private static final class ConnectionParameter {

		private ConnectionParameter() {
		};

		private static final String DB_DRIVER = "db.driver";
		private static final String DB_URL = "db.url";
		private static final String DB_USER = "db.user";
		private static final String DB_PASSWORD = "db.password";
		private static final String DB_POOL_SIZE = "db.poolsize";
	}
}
