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
import by.epam.totalizator.dao.SportDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

public class SportDAOImpl implements SportDAO {

	private static final Logger logger = LogManager.getLogger(SportDAOImpl.class.getName());

	@Override
	public List<Sport> obtainSportList(Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_SPORT_LIST_EN : SQLName.GET_SPORT_LIST_RU;

		List<Sport> sportList = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection()) {

			try (Statement statement = connection.createStatement()) {

				try (ResultSet result = statement.executeQuery(SQLProvider.getInstance().getSql(sqlName))) {

					while (result.next()) {
						Sport sport = new SportBuilder().buildId(result.getInt(1)).buildName(result.getString(2))
								.build();
						sportList.add(sport);
					}
				}
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Can't execute query", e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Connection Pool Exception", e);
		}
		return sportList;
	}
}
