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

import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.build.BettingBuilder;
import by.epam.totalizator.bean.build.ClubBuilder;
import by.epam.totalizator.bean.build.CompetitionBuilder;
import by.epam.totalizator.bean.build.SportBuilder;
import by.epam.totalizator.bean.build.TeamBuilder;
import by.epam.totalizator.bean.build.TournamentBuilder;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.dao.IBettingDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;

/**
 * #IBettingDAO interface implementation for the MySQL db {@link Betting}
 */
public class BettingDAOImpl implements IBettingDAO {

	private static final Logger LOGGER = LogManager.getLogger(BettingDAOImpl.class.getName());

	private static final String DAO_EXCEPTION_MESSAGE = "Can't execute query";
	private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection Pool Exception";

	/**
	 * Method adds a new record to the betting table
	 * 
	 * @param betting
	 *            bean object {@link Betting}
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public void makeBetting(Betting betting) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.ADD_BETTING;

		try (Connection connection = connectionPool.getConnection()) {
			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, betting.getCompetition().getId());
				prepStatement.setInt(2, betting.getUser().getId());
				prepStatement.setString(3, betting.getBetType().getShortName());
				prepStatement.setBigDecimal(4, betting.getBetRate());
				prepStatement.setBigDecimal(5, betting.getBetSize());

				prepStatement.execute();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
			throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
		} catch (ConnectionPoolException e) {
			LOGGER.log(Level.ERROR, e);
			throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Method receives a list of betting of the user by its id
	 * 
	 * @param userId
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordCount
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link Betting}
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public List<Betting> obtainUserBettingList(int userId, int fromRecord, int recordCount, Locale locale)
			throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_USER_BETTING_LIST_EN
				: SQLName.GET_USER_BETTING_LIST_RU;
		List<Betting> bettingList = new ArrayList<>(recordCount);

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, userId);
				prepStatement.setInt(2, fromRecord);
				prepStatement.setInt(3, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						BettingBuilder bettingBuilder = new BettingBuilder().buildId(resultSet.getInt(1))
								.buildBetTime(resultSet.getTimestamp(2));

						Sport sport = new SportBuilder().buildName(resultSet.getString(3)).build();
						Tournament tournament = new TournamentBuilder().buildSport(sport)
								.buildName(resultSet.getString(4)).build();
						Club homeClub = new ClubBuilder().buildName(resultSet.getString(5)).build();
						Team homeTeam = new TeamBuilder().buildSport(sport).buildClub(homeClub).build();
						Club awayClub = new ClubBuilder().buildName(resultSet.getString(6)).build();
						Team awayTeam = new TeamBuilder().buildSport(sport).buildClub(awayClub).build();
						Competition competition = new CompetitionBuilder().buildTournament(tournament).buildSport(sport)
								.buildHomeTeam(homeTeam).buildAwayTeam(awayTeam).build();
						bettingBuilder.buildCompetition(competition)
								.buildBetType(EventType.getTypeByShortName(resultSet.getString(7)))
								.buildBetSize(resultSet.getBigDecimal(8)).buildBetRate(resultSet.getBigDecimal(9))
								.buildGain(resultSet.getBigDecimal(10));
						User user = new UserBuilder().buildLocale(new Locale(resultSet.getString(11))).build();

						Betting betting = bettingBuilder.buildUser(user).build();
						bettingList.add(betting);
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
		return bettingList;
	}

	/**
	 * Method receives the total number of betting of the user by its id
	 * 
	 * @param IdUser
	 * @return number of entries
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public int obtainUserBettingCount(int userId) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.GET_USER_BETTING_COUNT;

		int count = 0;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setInt(1, userId);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
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
		return count;
	}

	/**
	 * Method receives a list of betting of the competition by its id
	 * 
	 * @param idCompetition
	 * @return {@link java.util.List} instances of {@link Betting}
	 * @throws DAOException
	 *             appears when {@link SQLException} or
	 *             {@link ConnectionPoolException} is detected
	 */
	@Override
	public List<Betting> obtainBettingListForComeptition(int idCompetition) throws DAOException {

		List<Betting> bettingList = new ArrayList<>();

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.GET_BETTING_LIST_BY_ID_COMPETITION;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, idCompetition);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {
						BettingBuilder bettingBuilder = new BettingBuilder().buildId(resultSet.getInt(1));

						Competition competition = new CompetitionBuilder().buildId(resultSet.getInt(2)).build();
						bettingBuilder.buildCompetition(competition);

						User user = new UserBuilder().buildId(resultSet.getInt(3)).build();
						bettingBuilder.buildUser(user);

						Betting betting = bettingBuilder
								.buildBetType(EventType.getTypeByShortName(resultSet.getString(4)))
								.buildBetRate(resultSet.getBigDecimal(5)).buildBetSize(resultSet.getBigDecimal(6))
								.build();

						bettingList.add(betting);
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
		return bettingList;
	}
}
