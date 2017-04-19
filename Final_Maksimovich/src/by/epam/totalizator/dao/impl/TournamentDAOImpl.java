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

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.bean.build.TournamentBuilder;
import by.epam.totalizator.dao.ITournamentDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

/**
 * #ITournamentDAO interface implementation for the MySQL db {@link Tournament}
 */
public class TournamentDAOImpl implements ITournamentDAO {

	private static final Logger LOGGER = LogManager.getLogger(TournamentDAOImpl.class.getName());

	private static final String DAO_EXCEPTION_MESSAGE = "Can't execute query";
	private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection Pool Exception";

	/**
	 * The method receives a list of tournaments by the sport
	 * 
	 * @param idSport
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Tournament}
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ?
							SQLName.GET_TOURNAMENT_LIST_BY_ID_SPORT_EN
							:SQLName.GET_TOURNAMENT_LIST_BY_ID_SPORT_RU;
		
		List<Tournament> tournamentList = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setInt(1, idSport);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					Tournament tournament = null;
					while (resultSet.next()) {

						tournament = new TournamentBuilder()
											.buildId(resultSet.getInt(1))
											.buildName(resultSet.getString(2))
											.build();
						
						tournamentList.add(tournament);
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
		return tournamentList;
	}
}