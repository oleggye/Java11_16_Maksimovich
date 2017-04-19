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

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.build.ClubBuilder;
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.bean.build.TeamBuilder;
import by.epam.totalizator.dao.ITeamDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

/**
 * #ITeamDAO interface implementation for the MySQL db {@link Team}
 */
public class TeamDAOImpl implements ITeamDAO {

	private static final Logger LOGGER = LogManager.getLogger(TeamDAOImpl.class.getName());

	private static final String DAO_EXCEPTION_MESSAGE = "Can't execute query";
	private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection Pool Exception";

	/**
	 * Method receives a list of teams by the sport
	 * 
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Team}
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public List<Team> obtainTeamListByIdSport(int idSport, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ?
							SQLName.GET_TEAM_LIST_BY_ID_SPORT_EN
							:SQLName.GET_TEAM_LIST_BY_ID_SPORT_RU;
		
		List<Team> teamList = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection()) {
			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				
				prepStatement.setInt(1, idSport);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {
						Club club = new ClubBuilder()
									.buildId(resultSet.getInt(1))
									.buildName(resultSet.getString(2))
									.build();
						Country country = new CountryBuilder()
										.buildId(resultSet.getInt(3))
										.buildName(resultSet.getString(4))
										.build();
						Team team = new TeamBuilder()
										.buildClub(club)
										.buildCountry(country)
										.build();
						
						teamList.add(team);
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
		return teamList;
	}
}