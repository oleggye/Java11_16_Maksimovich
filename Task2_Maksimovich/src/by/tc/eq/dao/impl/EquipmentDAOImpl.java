package by.tc.eq.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.tc.eq.bean.Equipment;
import by.tc.eq.dao.EquipmentDAO;
import by.tc.eq.dao.exeption.DAOException;

public class EquipmentDAOImpl implements EquipmentDAO {

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
	// сделать его общим)
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (con != null) {
			con.close();
		}

	}

	@Override
	public void addEquipment(Equipment equipment) throws DAOException {

		PreparedStatement prepStatement = null;
		DAOException exception = null;

		if (con != null) {

			try {

				String sql = "INSERT INTO `sportequipment`.`equipment` "
						+ "(`category`, `title`, `price`, `quantity`, `description`) " + "VALUES (?, ?, ?, ?, ?);";

				prepStatement = con.prepareStatement(sql);

				prepStatement.setInt(1, equipment.getCategory_id());
				prepStatement.setString(2, equipment.getTitle());
				prepStatement.setFloat(3, equipment.getPrice());
				prepStatement.setInt(4, equipment.getQuantity());
				prepStatement.setString(5, equipment.getDescription());

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
	// equipment
	@Override
	public void deleteEquipment(int id) throws DAOException {

		PreparedStatement prepStatement = null;
		DAOException exception = null;

		if (con != null) {
			try {
				String sql = "DELETE FROM `sportequipment`.`equipment` WHERE `id`=?;";
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
	public void updateEquipment(Equipment equipment) throws DAOException {

		PreparedStatement prepStatement = null;
		DAOException exception = null;

		if (con != null) {
			try {
				String sql = "UPDATE `sportequipment`.`equipment` "
						+ "SET `category`=?, `title`=?, `price`=?, `quantity`=?, `description`=? WHERE `id`=?;";
				prepStatement = con.prepareStatement(sql);

				prepStatement.setInt(1, equipment.getCategory_id());
				prepStatement.setString(2, equipment.getTitle());
				prepStatement.setFloat(3, equipment.getPrice());
				prepStatement.setInt(4, equipment.getQuantity());
				prepStatement.setString(5, equipment.getDescription());
				prepStatement.setInt(6, equipment.getId());

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

	public Equipment getEquipment(int id) throws DAOException {

		Statement statement = null;
		ResultSet resultSet = null;
		DAOException exception = null;

		Equipment equipment = new Equipment();

		if (con != null) {

			try {
				statement = con.createStatement();
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
					}
				}
			}

		} else {
			throw new DAOException("DB connection error");
		}

		return equipment;
	}

	public List<Equipment> getAllAvailableEquipment() throws DAOException {

		List<Equipment> equipmentslist = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {
			try {
				statement = con.createStatement();
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
		return equipmentslist;
	}

	@Override
	public List<Equipment> getAllRentedEquipment() throws DAOException {

		List<Equipment> equipmentslist = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {
			try {

				String sql = "select equipment.id, equipment.category, equipment.title, equipment.price,"
						+ " equipment.description from sportequipment.equipment "
						+ "where equipment.id in (select rent.equipment from sportequipment.rent where rent.status = \'Active\');";

				statement = con.createStatement();
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
					}
				}
			}
		} else {
			throw new DAOException("DB connection error");
		}
		return equipmentslist;
	}
}
