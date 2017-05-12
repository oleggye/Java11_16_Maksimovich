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
import by.epam.totalizator.dao.ICountryDAO;
import by.epam.totalizator.dao.connection.ConnectionProvider;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.ConnectionFactory;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

/**
 * #ICountryDAO interface implementation for the MySQL db {@link Country}
 */
public class CountryDAOImpl implements ICountryDAO {

	private static final Logger LOGGER = LogManager.getLogger(CountryDAOImpl.class.getName());

	private static final String DAO_EXCEPTION_MESSAGE = "Can't execute query";

	/**
	 * Method receives a list of countries
	 * 
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Country}
	 * @throws DAOException
	 *             appears when {@link SQLException} is detected
	 */
	@Override
	public List<Country> obtainCountryList(Locale locale) throws DAOException {

		ConnectionProvider connectionProvider = ConnectionFactory.getInstance().getConnectionProvider();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ?
							SQLName.GET_COUNTRY_LIST_EN 
							:SQLName.GET_COUNTRY_LIST_RU;

		List<Country> countryList = new ArrayList<>();

		try (Connection connection = connectionProvider.getConnection()) {
			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					Country country = null;
					while (resultSet.next()) {

						country = new CountryBuilder()
								.buildId(resultSet.getInt(1))
								.buildName(resultSet.getString(2))
								.build();
						countryList.add(country);
					}
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		}
		return countryList;
	}
}