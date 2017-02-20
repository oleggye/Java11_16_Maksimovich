package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.CompetitionSQLStrore;
import by.epam.totalizator.dao.util.sql.UserSQLStore;

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

		User user;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection.prepareStatement(UserSQLStore.SIGN_IN_USER_SQL)) {

				prepStatement.setString(1, login);
				prepStatement.setString(2, password);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						int id = resultSet.getInt(1);
						user = new User();
						user.setId(id);
						user.setEmail(login);
					} else {
						throw new DAOException("Wrong signIn params");
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
	public User getUserProfile(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
