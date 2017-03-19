package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.dao.CountryDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.CountrySQLStore;
import by.epam.totalizator.util.build.CountryBuilder;

public class CountryDAOImpl implements CountryDAO {

	@Override
	public List<Country> obtainCountryList() throws DAOException {

		List<Country> countryList = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement prepStatement = connection.prepareStatement(CountrySQLStore.GET_COUNTRY_LIST)) {

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
			throw new DAOException("Can't execute query", e);
		}
		return countryList;
	}
}
