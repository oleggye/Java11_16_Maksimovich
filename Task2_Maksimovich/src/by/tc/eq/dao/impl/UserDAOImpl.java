package by.tc.eq.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.tc.eq.bean.Status;
import by.tc.eq.bean.User;
import by.tc.eq.dao.UserDAO;
import by.tc.eq.dao.exeption.DAOException;
import by.tc.eq.dao.util.ConnectorDB;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement prepStatement = null;
		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "INSERT INTO `sportequipment`.`user` (`name`, `surname`, `login`, `password`,"
					+ "`discount`, `status`) VALUES (?, ?, ?, ?, ?, ?);";

			prepStatement = connection.prepareStatement(sql);

			// prepStatement.setInt(1, user.getId());
			prepStatement.setString(1, user.getName());
			prepStatement.setString(2, user.getSurname());
			prepStatement.setString(3, user.getLogin());
			prepStatement.setString(4, user.getPassword());
			prepStatement.setFloat(5, user.getDiscount());
			prepStatement.setString(6, user.getStatus().toString());

			prepStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (prepStatement != null) {
					prepStatement.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing PreparedStatement", e);
				}
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					// TODO:
				}
			}
		}
	}

	// FIXME: данный метод возвращает ОК, если даже по такому ID нет изначально
	// user
	@Override
	public void deleteUser(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement prepStatement = null;
		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "DELETE FROM `sportequipment`.`user` WHERE `id`=?;";
			prepStatement = connection.prepareStatement(sql);

			prepStatement.setInt(1, id);

			prepStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (prepStatement != null) {
					prepStatement.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing PreparedStatement", e);
				}
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					// TODO:
				}
			}
		}
	}

	@Override
	public void updateUser(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement prepStatement = null;
		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "UPDATE `sportequipment`.`user` "
					+ "SET `name`=?, `surname`=?, `discount`=?, `status`=? WHERE `id`=?;";

			prepStatement = connection.prepareStatement(sql);

			prepStatement.setString(1, user.getName());
			prepStatement.setString(2, user.getSurname());
			prepStatement.setFloat(3, user.getDiscount());
			prepStatement.setString(4, user.getStatus().toString());
			prepStatement.setInt(5, user.getId());

			prepStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (prepStatement != null) {
					prepStatement.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing PreparedStatement", e);
				}
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					// TODO:
				}
			}
		}
	}

	@Override
	public int signInUser(String login, String password) throws DAOException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			statement = connection.createStatement();
			String queryString = "select id from sportequipment.user where login = '" + login + "' and password ='"
					+ password + "';";
			resultSet = statement.executeQuery(queryString);

			if (resultSet.next()) {

				int id_user = resultSet.getInt(1);

				return id_user;
			} else {
				throw exception = new DAOException("No entries!");
			}

		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing ResultSet", e);
				}
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					if (exception != null) {

						exception.addSuppressed(e);
						throw exception;

					} else {
						throw exception = new DAOException("An error occurred during closing Statement", e);
					}
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						// TODO:
					}
				}
			}
		}
	}

	@Override
	public User getUser(int id) throws DAOException {

		User user = new User();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from sportequipment.user where id = " + id + ";");

			if (resultSet.next()) {

				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setSurname(resultSet.getString(3));
				user.setLogin(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				user.setDiscount(resultSet.getFloat(6));
				user.setStatus(Status.valueOf(resultSet.getString(7)));

			} else {
				throw new DAOException("Couldn't find user by id: '" + id + "'");
			}

		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing ResultSet", e);
				}
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					if (exception != null) {

						exception.addSuppressed(e);
						throw exception;

					} else {
						throw exception = new DAOException("An error occurred during closing Statement", e);
					}
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						// TODO:
					}
				}
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() throws DAOException {

		List<User> userslist = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from sportequipment.user;");

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setSurname(resultSet.getString(3));
				user.setLogin(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				user.setDiscount(resultSet.getFloat(6));
				user.setStatus(Status.valueOf(resultSet.getString(7)));

				userslist.add(user);
			}

		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing ResultSet", e);
				}
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					if (exception != null) {

						exception.addSuppressed(e);
						throw exception;

					} else {
						throw exception = new DAOException("An error occurred during closing Statement", e);
					}
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						// TODO:
					}
				}
			}
		}
		return userslist;
	}

	public List<User> getAllDebtors() throws DAOException {

		List<User> usersList = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from sportequipment.user where status = \'Debtor\';");

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setSurname(resultSet.getString(3));
				user.setLogin(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				user.setDiscount(resultSet.getFloat(6));
				user.setStatus(Status.valueOf(resultSet.getString(7)));

				usersList.add(user);
			}

		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			throw exception = new DAOException("An error occurred during execution", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				if (exception != null) {

					exception.addSuppressed(e);
					throw exception;

				} else {
					throw exception = new DAOException("An error occurred during closing ResultSet", e);
				}
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					if (exception != null) {

						exception.addSuppressed(e);
						throw exception;

					} else {
						throw exception = new DAOException("An error occurred during closing Statement", e);
					}
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						// TODO:
					}
				}
			}
		}
		return usersList;
	}

}
