package by.epam.totalizator.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.bean.build.PhoneBuilder;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());
	private static final String DAO_EXCEPTION_MESSAGE = "Can't execute query";
	private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection Pool Exception";

	@Override
	public User signIn(String login, String password) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		SQLName sqlName = SQLName.SIGN_IN_USER_SQL;

		User user = null;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setString(1, login);
				prepStatement.setString(2, password);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {

						user = new UserBuilder().buildId(resultSet.getInt(1))
								.buildUserType(UserType.getTypeByShortName(resultSet.getString(2)))
								.buildBalance(resultSet.getBigDecimal(3)).buildCurrency(resultSet.getString(4))
								.buildLocale(new Locale(resultSet.getString(5))).buildEmail(login)
								.buildBanned(resultSet.getBoolean(6)).build();
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
		return user;

	}

	@Override
	public void registration(User user) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.ADD_NEW_USER;

		try (Connection connection = connectionPool.getConnection()) {
			try (PreparedStatement prepareStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				connection.setAutoCommit(false);

				prepareStatement.setString(1, user.getFirstName());
				prepareStatement.setString(2, user.getLastName());
				prepareStatement.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
				prepareStatement.setString(4, user.getEmail());
				prepareStatement.setInt(5, user.getCountry().getId());
				prepareStatement.setString(6, user.getCity());
				prepareStatement.setString(7, user.getPhone().getCode());
				prepareStatement.setString(8, user.getPhone().getPhoneNumber());
				prepareStatement.setString(9, user.getCurrency());
				prepareStatement.setString(10, user.getLocale().toString());

				prepareStatement.execute();

				int idUser = getNewUserId(connection);
				user.setId(idUser);

				appendPrivateDataIntoUser(user, connection);

				connection.commit();
				connection.setAutoCommit(true);

			} catch (SQLException ex) {

				connection.rollback();
				connection.setAutoCommit(true);
				throw ex;
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
	}

	private int getNewUserId(Connection connection) throws SQLException {

		int idNewUser = 0;

		SQLName sqlName = SQLName.GET_LAST_INSERTED_ID;

		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

			try (ResultSet resultSet = prepareStatement.executeQuery()) {
				if (resultSet.next()) {
					idNewUser = resultSet.getInt(1);
				}
			}
		}
		return idNewUser;
	}

	private void appendPrivateDataIntoUser(User user, Connection connection) throws SQLException {

		SQLName sqlName = SQLName.INSERT_INTO_USER_PRIVATE_SQL;

		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

			prepareStatement.setInt(1, user.getId());
			prepareStatement.setInt(2, user.getSecredQuestionId());
			prepareStatement.setString(3, user.getSecredQuestionAnswer());
			prepareStatement.setString(4, user.getPassword());

			prepareStatement.execute();
		}
	}

	@Override
	public boolean checkLogin(String login) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.GET_USER_ID_BY_EMAIL_SQL;
		boolean result = false;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setString(1, login);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						result = true;
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
		return result;
	}

	@Override
	public User getUserProfile(int id, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_USER_BY_ID_SQL_EN : SQLName.GET_USER_BY_ID_SQL_RU;
		User user = null;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, id);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						UserBuilder userBuilder = new UserBuilder().buildFirstName(resultSet.getString(1))
								.buildLastName(resultSet.getString(2)).buildDateOfBirth(resultSet.getDate(3))
								.buildEmail(resultSet.getString(4));

						Country country = new Country();
						country.setName(resultSet.getString(6));

						user = userBuilder.buildCountry(country).buildCity(resultSet.getString(6))
								.buildPhone(new Phone(resultSet.getString(7), resultSet.getString(8)))
								.buildCurrency(resultSet.getString(9)).buildBalance(resultSet.getBigDecimal(10))
								.buildLocale(new Locale(resultSet.getString(11))).build();
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
		return user;
	}

	@Override
	public List<User> obtainUserList(int fromRecord, int recordCount, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_USER_LIST_EN : SQLName.GET_USER_LIST_RU;

		List<User> userList = new ArrayList<>(recordCount);

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, fromRecord);
				prepStatement.setInt(2, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						UserBuilder userBuilder = new UserBuilder().buildId(resultSet.getInt(1))
								.buildFirstName(resultSet.getString(2)).buildLastName(resultSet.getString(3))
								.buildDateOfBirth(resultSet.getDate(4)).buildEmail(resultSet.getString(5));

						Country country = new CountryBuilder().buildId(resultSet.getInt(6))
								.buildName(resultSet.getString(7)).build();

						userBuilder.buildCountry(country).buildCity(resultSet.getString(8));

						Phone phone = new PhoneBuilder().buildCode(resultSet.getString(9))
								.buildPhoneNumber(resultSet.getString(10)).build();

						userBuilder.buildPhone(phone).buildCurrency(resultSet.getString(11))
								.buildBalance(resultSet.getBigDecimal(12));

						UserType userType = UserType.getTypeByShortName(resultSet.getString(13));

						userBuilder.buildUserType(userType).buildRegistrationTime(resultSet.getTimestamp(14))
								.buildLocale(new Locale(resultSet.getString(15))).buildBanned(resultSet.getBoolean(16));

						User user = userBuilder.build();

						userList.add(user);
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
		return userList;
	}

	@Override
	public int obtainAllUserCount() throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.GET_USER_LIST_COUNT;

		int count = 0;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	@Override
	public void banUser(int idUser) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.BAN_USER;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, idUser);
				prepStatement.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void unbanUser(int idUser) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.UNBAN_USER;
		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, idUser);
				prepStatement.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void changeUserType(int idUser, UserType userType) throws DAOException {
		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.UPDATE_USER_TYPE;
		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setString(1, userType.getShortName());
				prepStatement.setInt(2, idUser);

				prepStatement.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void changeUserBalance(int idUser, BigDecimal amount) throws DAOException {
		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.CALL_CHANGE_USER_BALANCE;

		try (Connection connection = connectionPool.getConnection()) {
			try (CallableStatement statement = connection.prepareCall(SQLProvider.getInstance().getSql(sqlName))) {
				statement.setInt(1, idUser);
				statement.setBigDecimal(2, amount);

				statement.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
	}
}
