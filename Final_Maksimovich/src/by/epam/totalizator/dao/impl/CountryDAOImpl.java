package by.epam.totalizator.dao.impl;

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
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.dao.CountryDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

public class CountryDAOImpl implements CountryDAO {
	
	private static final Logger logger = LogManager.getLogger(CountryDAOImpl.class.getName());

	@Override
	public List<Country> obtainCountryList(Locale locale) throws DAOException {
		
		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_COUNTRY_LIST_EN : SQLName.GET_COUNTRY_LIST_RU;

		List<Country> countryList = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection()) {
			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					Country country = null;
					while (resultSet.next()) {

						country = new CountryBuilder().buildId(resultSet.getInt(1)).buildName(resultSet.getString(2))
								.build();
						countryList.add(country);
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
		return countryList;
	}
}
