package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Locale;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.UserSQLStore;
import by.epam.totalizator.util.build.UserBuilder;

public class UserDAOImpl implements UserDAO {

	// TODO: заглушка
	public User tempSignIn(String login, String password) throws DAOException {

		String myLogin = "admin@example.com";
		String myPassword = "123";
		if (login.equals(myLogin) && password.equals(myPassword)) {
			User user = new User();
			user.setId(1);
			user.setEmail(login);
			user.setPassword(password);
			return user;
		} else {
			throw new DAOException("Wrong signIn params");
		}

	}

	@Override
	public User signIn(String login, String password) throws DAOException {

		User user = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection.prepareStatement(UserSQLStore.SIGN_IN_USER_SQL)) {

				prepStatement.setString(1, login);
				prepStatement.setString(2, password);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {

						user = new UserBuilder()
								.buildId(resultSet.getInt(1))
								.buildUserType(UserType.getTypeByShortName(resultSet.getString(2)))
								.buildBalance(resultSet.getBigDecimal(3))
								.buildLocale(Locale.getLocaleByShortName(resultSet.getString(4)))
								.buildEmail(login)
								.build();
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException("Can't execute query", e);
		}
		return user;

	}

	@Override
	public void registration(User user) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkLogin(String login) throws DAOException {

		boolean result = false;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection.prepareStatement(UserSQLStore.GET_USER_ID_BY_EMAIL_SQL)) {

				prepStatement.setString(1, login);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						result = true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException("Can't execute query", e);
		}
		return result;
	}

	@Override
	public User getUserProfile(int id) throws DAOException {
		User user = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection.prepareStatement(UserSQLStore.GET_USER_BY_ID_SQL)){
				prepStatement.setInt(1, id);
				
				try(ResultSet resultSet = prepStatement.executeQuery()){
					
					if(resultSet.next()){
						UserBuilder userBuilder = new UserBuilder()
								.buildFirstName(resultSet.getString(1))
								.buildLastName(resultSet.getString(2))
								.buildDateOfBirth(resultSet.getDate(3))
								.buildEmail(resultSet.getString(4));
						
						Country country = new Country();
						country.setName(resultSet.getString(6));
								
						user = userBuilder.buildCountry(country)
										  .buildCity(resultSet.getString(6))
										  .buildPhone(new Phone(resultSet.getString(7),resultSet.getString(8)))
										  .buildCurrency(resultSet.getString(9))
										  .buildBalance(resultSet.getBigDecimal(10))
										  .buildLocale(Locale.getLocaleByShortName(resultSet.getString(11)))
										  .build();			
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException("Can't execute query", e);
		}
		return user;
	}

	@Override
	public void deleteUser(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) throws DAOException {
		// TODO Auto-generated method stub

	}
}
