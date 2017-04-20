package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.build.SportBuilder;
import by.epam.totalizator.dao.ISportDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

/**
 * #ISportDAO interface implementation for the MySQL db {@link Sport}
 */
public class SportDAOImpl implements ISportDAO {

	private static final Logger LOGGER = LogManager.getLogger(SportDAOImpl.class.getName());

	private static final String DAO_EXCEPTION_MESSAGE = "Can't execute query";
	private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection Pool Exception";

	/**
	 * Method receives a list of sports
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Sport}
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public List<Sport> obtainSportList(Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ?
							SQLName.GET_SPORT_LIST_EN 
							:SQLName.GET_SPORT_LIST_RU;

		List<Sport> sportList = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection()) {

			try (Statement statement = connection.createStatement()) {

				try (ResultSet result = statement.executeQuery(SQLProvider.getInstance().getSql(sqlName))) {

					while (result.next()) {
						Sport sport = new SportBuilder()
											.buildId(result.getInt(1))
											.buildName(result.getString(2))
											.build();
						sportList.add(sport);
					}
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			LOGGER.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
		return sportList;
	}
}
