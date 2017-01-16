package by.tc.eq.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import by.tc.eq.bean.Equipment;
import by.tc.eq.bean.Ower;
import by.tc.eq.bean.User;
import by.tc.eq.dao.RentDAO;
import by.tc.eq.dao.exeption.DAOException;
import by.tc.eq.dao.factory.DAOFactory;

public class RentDAOImpl implements RentDAO {

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
	public void rentEquipment(int id_user, int[] ids_equipment) throws DAOException {

		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {

			try {

				con.setAutoCommit(false);

				/**
				 * <pre>
				 * 1) необходимо проверить статус данного пользователя и если он
				 * 'Debtor' (должник) - выбросить исключение, также как и если
				 * пользователя с таким id нет в БД
				 * </pre>
				 */

				statement = con.createStatement();
				resultSet = statement
						.executeQuery("select user.status from sportequipment.user where user.id =" + id_user + ";");

				if (!resultSet.next() || !(resultSet.getString(1).equals("Available"))) {
					throw exception = new DAOException("The user cann't rent equipment!");
				}

				/**
				 * <pre>
				 * 2) здесь мы выбираем из массива ids_equipment, id каждого
				 * оборудования и передаем id_equipment и id_user в хранимую
				 * функцию для аренды.
				 * Функция последовательно выполняет следующие действия:
				 * а) проверяет количество товара по id
				 * - если < 0, то функция возвращает 0 (false), т.е. аренда не удалась
				 * - если > 0, то продолжаем, 
				 * б) уменьшаем на 1 значение поля количество (quantity) на 1
				 * в) добавляем запись в таблицу аренды
				 * г) возвращает 1(true) - оборудование арендовано
				 * 
				 * (!код хранимой функции приложен в файле additional.txt!)
				 * </pre>
				 */

				// TODO: здесь, возможно, лучше использовать PreparedStatement
				for (int id_equipment : ids_equipment) {
					resultSet = statement.executeQuery(
							"select sportequipment.rent_equipment(" + id_user + ", " + id_equipment + ");");

					if (!resultSet.next() || !(resultSet.getInt(1) == 1)) {
						throw exception = new DAOException("The user cann't rent equipment!");
					}
				}
				/** 3) изменяем status user с 'Available' на 'Debtor' */

				statement.executeUpdate(
						"UPDATE `sportequipment`.`user` SET `status`='Debtor' WHERE `id`=" + id_user + ";");

				con.commit();

			} catch (SQLException e) {
				exception = new DAOException("An error occurred during execution", e);
				try {
					con.rollback();
				} catch (SQLException ex) {
					exception.addSuppressed(ex);
					throw exception;
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

		else
			throw new DAOException("DB connection error");
	}

	@Override
	public void returnEquipment(int id_user, int[] ids_equipment) throws DAOException {

		Statement statement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		if (con != null) {

			try {

				con.setAutoCommit(false);

				statement = con.createStatement();

				// TODO: можно использовать для подсчета суммы, которую должен
				// оплатить пользователь
				float total_price = 0;

				/**
				 * <pre>
				 * 1) здесь мы выбираем из массива ids_equipment, id каждого
				 * оборудования и передаем id_equipment и id_user в хранимую
				 * функцию для возврата снаряжения.
				 * 	Функция последовательно выполняет следующие действия:
				 * а) проверяет количество товара по id
				 * - если < 0, то функция возвращает 0 (false), т.е. аренда не удалась;
				 * - если > 0, то  функция возвращает 1 (true), т.ею такой товар есть 
				 * и его можно арендовать.
				 * б) уменьшаем на 1 значение поля количество (quantity) на 1 товара с этим id
				 * в) добавляем запись в таблицу аренды
				 * г) возвращает 1(true) - оборудование арендовано
				 * 
				 * (!код хранимой функции приложен в файле additional.txt!)
				 * </pre>
				 */

				// TODO: здесь, возможно, лучше использовать PreparedStatement
				for (int id_equipment : ids_equipment) {
					resultSet = statement.executeQuery(
							"select sportequipment.return_equipment(" + id_user + ", " + id_equipment + ");");

					if (resultSet.next()) {
						total_price = total_price + resultSet.getFloat(1);

					} else {
						throw exception = new DAOException("The user cann't return equipment!");
					}
				}
				/**
				 * <pre>
				 * 2) вызов хранимой функции, с помощью которой
				 * проверяем пользователя:
				 * - если он все вернул, то возвращается 1 (true)и изменяем
				 *  status с 'Debtor' на 'Available';
				 * - если не все, то оставляем как есть
				 * 
				 * (!код хранимой функции приложен в файле additional.txt!)
				 * </pre>
				 */

				resultSet = statement.executeQuery("select sportequipment.is_user_free(" + id_user + ");");
				if (resultSet.next() && (resultSet.getInt(1) == 1)) {

					statement.executeUpdate(
							"UPDATE `sportequipment`.`user` SET `status`='Available' WHERE `id`=" + id_user + ";");

				}

				con.commit();

			} catch (SQLException e) {
				exception = new DAOException("An error occurred during execution", e);
				try {
					con.rollback();
				} catch (SQLException ex) {
					exception.addSuppressed(ex);
					throw exception;
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

		else
			throw new DAOException("DB connection error");
	}

	@Override
	public List<Ower> getAllOwers() throws DAOException {

		List<Ower> owers = new ArrayList<>();

		if (con != null) {

			try {
				DAOFactory factory = DAOFactory.getInstance();

				List<User> users = factory.getUserDAO().getAllDebtors();

				for (User u : users) {

					int u_id = u.getId();

					Ower ower = new Ower();

					ower.setUser(u);

					List<Equipment> list = getAllOwedEquipment(u_id);

					ower.setEquipments(list);

					owers.add(ower);
				}

			} catch (DAOException e) {
				throw new DAOException("An error occurred during execution", e);
			}

		} else {
			throw new DAOException("DB connection error");
		}

		return owers;
	}

	private List<Equipment> getAllOwedEquipment(int userId) throws DAOException {

		// 1-т.к. за один раз можно брать
		// только 1 вещь
		final int RENT_QUANTITY_PER_EQUIPMENT = 1;

		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		DAOException exception = null;

		List<Equipment> list = new ArrayList<>();

		if (con != null) {

			try {

				String sql = "select * from sportequipment.equipment where id IN (SELECT equipment FROM sportequipment.rent where (status = \'Active\' ) and user =?);";
				prepStatement = con.prepareStatement(sql);

				prepStatement.setInt(1, userId);

				resultSet = prepStatement.executeQuery();

				while (resultSet.next()) {
					Equipment equipment = new Equipment();

					equipment.setId(resultSet.getInt(1));
					equipment.setCategory_id(Integer.valueOf(resultSet.getString(2)));
					equipment.setTitle(resultSet.getString(3));
					equipment.setPrice(resultSet.getFloat(4));
					// equipment.setQuantity(Integer.valueOf(resultSet.getString(5)));
					equipment.setQuantity(RENT_QUANTITY_PER_EQUIPMENT);
					equipment.setDescription(resultSet.getString(6));

					list.add(equipment);
				}
			} catch (SQLException e) {
				throw exception = new DAOException("An error occurred during execution", e);
			} catch (NumberFormatException e) {
				throw exception = new DAOException("An error occurred during parsing", e);

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
						if (prepStatement != null) {
							prepStatement.close();
						}
					} catch (SQLException e) {
						if (exception != null) {

							exception.addSuppressed(e);
							throw exception;

						} else {
							throw exception = new DAOException("An error occurred during closing PrepStatement", e);
						}
					}
				}
			}

		} else {
			throw new DAOException("DB connection error");
		}
		return list;
	}
}
