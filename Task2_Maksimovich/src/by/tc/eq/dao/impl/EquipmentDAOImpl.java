package by.tc.eq.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.tc.eq.bean.Equipment;
import by.tc.eq.dao.EquipmentDAO;
import by.tc.eq.dao.exeption.DAOException;
import by.tc.eq.dao.util.ConnectorDB;

public class EquipmentDAOImpl implements EquipmentDAO {

	@Override
	public void addEquipment(Equipment equipment) throws DAOException {

		Connection connection = null;
		PreparedStatement prepStatement = null;
		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "INSERT INTO `sportequipment`.`equipment` "
					+ "(`category`, `title`, `price`, `quantity`, `description`) " + "VALUES (?, ?, ?, ?, ?);";

			prepStatement = connection.prepareStatement(sql);

			prepStatement.setInt(1, equipment.getCategory_id());
			prepStatement.setString(2, equipment.getTitle());
			prepStatement.setFloat(3, equipment.getPrice());
			prepStatement.setInt(4, equipment.getQuantity());
			prepStatement.setString(5, equipment.getDescription());

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
	// equipment
	@Override
	public void deleteEquipment(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement prepStatement = null;
		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "DELETE FROM `sportequipment`.`equipment` WHERE `id`=?;";
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
	public void updateEquipment(Equipment equipment) throws DAOException {

		Connection connection = null;
		PreparedStatement prepStatement = null;
		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "UPDATE `sportequipment`.`equipment` "
					+ "SET `category`=?, `title`=?, `price`=?, `quantity`=?, `description`=? WHERE `id`=?;";
			prepStatement = connection.prepareStatement(sql);

			prepStatement.setInt(1, equipment.getCategory_id());
			prepStatement.setString(2, equipment.getTitle());
			prepStatement.setFloat(3, equipment.getPrice());
			prepStatement.setInt(4, equipment.getQuantity());
			prepStatement.setString(5, equipment.getDescription());
			prepStatement.setInt(6, equipment.getId());

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

	public Equipment getEquipment(int id) throws DAOException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		DAOException exception = null;

		Equipment equipment = new Equipment();

		try {
			connection = ConnectorDB.getConnection();

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from sportequipment.equipment where id =" + id + ";");

			if (resultSet.next()) {
				equipment.setId(resultSet.getInt(1));
				equipment.setCategory_id(Integer.valueOf(resultSet.getInt(2)));
				equipment.setTitle(resultSet.getString(3));
				equipment.setPrice(resultSet.getFloat(4));
				equipment.setQuantity(resultSet.getInt(5));
				equipment.setDescription(resultSet.getString(6));

			} else {
				throw new DAOException("Couldn't find equipment by id: '" + id + "'");
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
		return equipment;
	}

	public List<Equipment> getAllAvailableEquipment() throws DAOException {

		List<Equipment> equipmentslist = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from sportequipment.equipment;");

			while (resultSet.next()) {
				Equipment equipment = new Equipment();

				equipment.setId(resultSet.getInt(1));
				equipment.setCategory_id(Integer.valueOf(resultSet.getString(2)));
				equipment.setTitle(resultSet.getString(3));
				equipment.setPrice(resultSet.getFloat(4));
				equipment.setQuantity(resultSet.getInt(5));
				equipment.setDescription(resultSet.getString(6));

				equipmentslist.add(equipment);
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
		return equipmentslist;
	}

	@Override
	public List<Equipment> getAllRentedEquipment() throws DAOException {

		List<Equipment> equipmentslist = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		try {
			connection = ConnectorDB.getConnection();

			String sql = "select equipment.id, equipment.category, equipment.title, equipment.price,"
					+ " equipment.description from sportequipment.equipment "
					+ "where equipment.id in (select rent.equipment from sportequipment.rent where rent.status = \'Active\');";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Equipment equipment = new Equipment();

				equipment.setId(resultSet.getInt(1));
				equipment.setCategory_id(Integer.valueOf(resultSet.getString(2)));
				equipment.setTitle(resultSet.getString(3));
				equipment.setPrice(resultSet.getFloat(4));
				equipment.setQuantity(1);// 1 -т.к. арендуется по одному
											// снаряжению за раз
				equipment.setDescription(resultSet.getString(5));

				equipmentslist.add(equipment);
			}

		} catch (ClassNotFoundException e) {
			throw new DAOException("Can't initialize driver");

		} catch (SQLException e) {
			System.out.println(e);
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
		return equipmentslist;
	}
}
