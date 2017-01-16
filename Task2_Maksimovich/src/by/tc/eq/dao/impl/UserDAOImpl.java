package by.tc.eq.dao.impl;

import java.sql.DriverManager;
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

import com.mysql.jdbc.Connection;

public class UserDAOImpl implements UserDAO {

	private static Connection con;

	// TODO: вынести в пул соединений
	{

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/sportequipment", "test", "123");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// TODO: убрать этот метод и реализовать пул соединений (не придумал, как
	// сделать один пул на все приложение + его закрывать)
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (con != null) {
			con.close();
		}

	}

	@Override
	public void addUser(User user) throws DAOException {

		PreparedStatement prepStatement = null;
		DAOException exception = null;

		if (con != null) {

			try {
				String sql = "INSERT INTO `sportequipment`.`user` (`name`, `surname`, `login`, `password`,"
						+ "`discount`, `status`) VALUES (?, ?, ?, ?, ?, ?);";

				prepStatement = con.prepareStatement(sql);

				// prepStatement.setInt(1, user.getId());
				prepStatement.setString(1, user.getName());
				prepStatement.setString(2, user.getSurname());
				prepStatement.setString(3, user.getLogin());
				prepStatement.setString(4, user.getPassword());
				prepStatement.setFloat(5, user.getDiscount());
				prepStatement.setString(6, user.getStatus().toString());

				prepStatement.executeUpdate();

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
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}

	}

	// FIXME: данный метод возвращает ОК, если даже по такому ID нет изначально
	// user
	@Override
	public void deleteUser(int id) throws DAOException {

		PreparedStatement prepStatement = null;
		DAOException exception = null;

		if (con != null) {

			try {
				String sql = "DELETE FROM `sportequipment`.`user` WHERE `id`=?;";
				prepStatement = con.prepareStatement(sql);

				prepStatement.setInt(1, id);

				prepStatement.executeUpdate();
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
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}

	}

	@Override
	public void updateUser(User user) throws DAOException {

		PreparedStatement prepStatement = null;
		DAOException exception = null;

		if (con != null) {

			try {
				String sql = "UPDATE `sportequipment`.`user` "
						+ "SET `name`=?, `surname`=?, `discount`=?, `status`=? WHERE `id`=?;";

				prepStatement = con.prepareStatement(sql);

				prepStatement.setString(1, user.getName());
				prepStatement.setString(2, user.getSurname());
				prepStatement.setFloat(3, user.getDiscount());
				prepStatement.setString(4, user.getStatus().toString());
				prepStatement.setInt(5, user.getId());

				prepStatement.executeUpdate();
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
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}
	}

	@Override
	public int signInUser(String login, String password) throws DAOException {

		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {
			try {

				statement = con.createStatement();
				String queryString = "select id from sportequipment.user where login = '" + login + "' and password ='"
						+ password + "';";
				resultSet = statement.executeQuery(queryString);

				if (resultSet.next()) {

					int id_user = resultSet.getInt(1);

					return id_user;
				} else {
					throw exception = new DAOException("No entries!");
				}

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
					}
				}
			}

		} else {
			throw new DAOException("DB connection error");
		}
	}

	@Override
	public User getUser(int id) throws DAOException {

		User user = new User();
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {
			try {
				statement = con.createStatement();
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
					}
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}

		return user;
	}

	@Override
	public List<User> getAllUsers() throws DAOException {

		List<User> userslist = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {
			try {
				statement = con.createStatement();
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
					}
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}
		return userslist;
	}

	public List<User> getAllDebtors() throws DAOException {

		List<User> usersList = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {
			try {
				statement = con.createStatement();
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
					}
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}
		return usersList;
	}

}
