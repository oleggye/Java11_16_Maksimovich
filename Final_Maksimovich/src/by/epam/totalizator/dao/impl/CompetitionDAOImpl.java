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
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.bean.build.ClubBuilder;
import by.epam.totalizator.bean.build.CompetitionBuilder;
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.bean.build.SportBuilder;
import by.epam.totalizator.bean.build.TeamBuilder;
import by.epam.totalizator.bean.build.TournamentBuilder;
import by.epam.totalizator.dao.CompetitionDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;
import by.epam.totalizator.dao.util.SQLName;
import by.epam.totalizator.dao.util.SQLProvider;
import by.epam.totalizator.dao.util.sql.CompetitionSQLStrore;

public class CompetitionDAOImpl implements CompetitionDAO {

	private static final Logger logger = LogManager.getLogger(CompetitionDAOImpl.class.getName());

	@Override
	public void addCompetition(Competition competition) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection.prepareStatement(CompetitionSQLStrore.ADD_COMPETITION)) {
				prepStatement.setInt(1, competition.getSport().getId());
				prepStatement.setInt(2, competition.getTournament().getId());
				prepStatement.setInt(3, competition.getHomeTeam().getClub().getId());
				prepStatement.setInt(4, competition.getAwayTeam().getClub().getId());
				prepStatement.setInt(5, competition.getCountry().getId());
				java.sql.Timestamp startTimeSql = new java.sql.Timestamp(competition.getStartTime().getTime());
				prepStatement.setTimestamp(6, startTimeSql);

				prepStatement.execute();

			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Can't execute query", e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Connection Pool Exception", e);
		}
	}

	@Override
	public Competition obtainCompetition(int id, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_COMPETITION_BY_ID_EN
				: SQLName.GET_COMPETITION_BY_ID_RU;
		Competition competition = null;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, id);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder()
								.buildStartTime(resultSet.getTimestamp(1));

						Sport sport = new SportBuilder().buildId(resultSet.getInt(2)).buildName(resultSet.getString(3))
								.build();
						Tournament tournament = new TournamentBuilder().buildId(resultSet.getInt(4))
								.buildName(resultSet.getString(5)).build();
						Club homeTeamClub = new ClubBuilder().buildId(resultSet.getInt(6))
								.buildName(resultSet.getString(7)).build();
						Team homeTeam = new TeamBuilder().buildClub(homeTeamClub).build();
						Club awayTeamClub = new ClubBuilder().buildId(resultSet.getInt(8))
								.buildName(resultSet.getString(9)).build();
						Team awayTeam = new TeamBuilder().buildClub(awayTeamClub).build();

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildId(resultSet.getInt(10))
								.buildName(resultSet.getString(11)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(12))
								.buildDrawRate(resultSet.getBigDecimal(13))
								.buildWinAwayRate(resultSet.getBigDecimal(14));

						String resultParam = resultSet.getString(15);
						EventType result = null;
						if (resultParam != null) {
							result = EventType.getTypeByShortName(resultParam);
						}

						competition = competitionBuilder.buildResult(result).build();
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

		return competition;
	}

	@Override
	public void updateCompetition(Competition competition) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.UPDATE_COMPETITION;
		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, competition.getSport().getId());
				prepStatement.setInt(2, competition.getTournament().getId());
				prepStatement.setInt(3, competition.getHomeTeam().getClub().getId());
				prepStatement.setInt(4, competition.getAwayTeam().getClub().getId());
				prepStatement.setInt(5, competition.getCountry().getId());
				java.sql.Timestamp startTimeSql = new java.sql.Timestamp(competition.getStartTime().getTime());
				prepStatement.setTimestamp(6, startTimeSql);

				if (competition.getResult() == null) {
					prepStatement.setNull(7, java.sql.Types.VARCHAR);
				} else {
					prepStatement.setString(7, competition.getResult().getShortName());
				}
				prepStatement.setInt(8, competition.getId());

				prepStatement.execute();

			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Can't execute query", e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Connection Pool Exception", e);
		}
	}

	@Override
	public void updateAllCompetitionRate(Competition competition) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.UPDATE_All_COMPETITION_RATE;
		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setBigDecimal(1, competition.getWinHomeRate());
				prepStatement.setBigDecimal(2, competition.getDrawRate());
				prepStatement.setBigDecimal(3, competition.getWinAwayRate());
				prepStatement.setInt(4, competition.getId());

				prepStatement.execute();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Can't execute query", e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Connection Pool Exception", e);
		}
	}

	@Override
	public void deleteCompetition(int idUser) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.CHANGE_COMPETITION_STATUS;
		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, idUser);

				prepStatement.execute();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Can't execute query", e);
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOException("Connection Pool Exception", e);
		}
	}

	@Override
	public List<Competition> obtainCompetitionList(int fromRecord, int recordCount, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		
		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_COMPETITION_LIST_EN
				: SQLName.GET_COMPETITION_LIST_RU;
		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setInt(1, fromRecord);
				prepStatement.setInt(2, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder().buildId(resultSet.getInt(1))
								.buildStartTime(resultSet.getTimestamp(2));

						Sport sport = new SportBuilder().buildId(resultSet.getInt(3)).buildName(resultSet.getString(4))
								.build();
						Tournament tournament = new TournamentBuilder().buildName(resultSet.getString(5)).build();
						Club homeTeamClub = new ClubBuilder().buildName(resultSet.getString(6)).build();
						Team homeTeam = new TeamBuilder().buildClub(homeTeamClub).build();
						Club awayTeamClub = new ClubBuilder().buildName(resultSet.getString(7)).build();
						Team awayTeam = new TeamBuilder().buildClub(awayTeamClub).build();

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildName(resultSet.getString(8)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(9))
								.buildDrawRate(resultSet.getBigDecimal(10))
								.buildWinAwayRate(resultSet.getBigDecimal(11));

						String resultParam = resultSet.getString(12);

						if (resultParam != null) {
							competitionBuilder.buildResult(EventType.getTypeByShortName(resultParam));
						}

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
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

		return competitionList;
	}

	@Override
	public int obtainCompetitionListCount() throws DAOException {
		
		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = SQLName.GET_COMPETITION_LIST_COUNT;

		int count = 0;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
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
		return count;
	}

	@Override
	public int obtainAvailableCompetitionListCount(boolean isPreparedOnly) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = isPreparedOnly ? SQLName.GET_PREPARED_AVAILABLE_COMPETITION_LIST_COUNT
				: SQLName.GET_AVAILABLE_COMPETITION_LIST_COUNT;
		int count = 0;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
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
		return count;
	}

	@Override
	public List<Competition> obtainAvailableCompetitionList(int fromRecord, int recordCount, boolean isPreparedOnly,
			Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		SQLName sqlName = null;

		if (locale.equals(Locale.ENGLISH)) {
			sqlName = isPreparedOnly ? SQLName.GET_PREPARED_AVAILABLE_COMPETITION_LIST_EN
					: SQLName.GET_AVAILABLE_COMPETITION_LIST_EN;
		} else {
			sqlName = isPreparedOnly ? SQLName.GET_PREPARED_AVAILABLE_COMPETITION_LIST_RU
					: SQLName.GET_AVAILABLE_COMPETITION_LIST_RU;
		}

		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setInt(1, fromRecord);
				prepStatement.setInt(2, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder().buildId(resultSet.getInt(1))
								.buildStartTime(resultSet.getTimestamp(2));

						Sport sport = new SportBuilder().buildName(resultSet.getString(3)).build();
						Tournament tournament = new TournamentBuilder().buildName(resultSet.getString(4)).build();
						Club homeTeamClub = new ClubBuilder().buildName(resultSet.getString(5)).build();
						Team homeTeam = new TeamBuilder().buildClub(homeTeamClub).build();
						Club awayTeamClub = new ClubBuilder().buildName(resultSet.getString(6)).build();
						Team awayTeam = new TeamBuilder().buildClub(awayTeamClub).build();

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildName(resultSet.getString(7)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(8))
								.buildDrawRate(resultSet.getBigDecimal(9))
								.buildWinAwayRate(resultSet.getBigDecimal(10));

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
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

		return competitionList;
	}

	@Override
	public List<Competition> obtainSpecialCompetitionList(int idSport, int idTournament, int fromRecord,
			int recordCount, boolean isPreparedOnly, Locale locale) throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		SQLName sqlName = null;

		if (locale.equals(Locale.ENGLISH)) {
			sqlName = isPreparedOnly ? SQLName.GET_PREPARED_SPECIAL_COMPETITION_LIST_EN
					: SQLName.GET_SPECIAL_COMPETITION_LIST_EN;
		} else {
			sqlName = isPreparedOnly ? SQLName.GET_PREPARED_SPECIAL_COMPETITION_LIST_RU
					: SQLName.GET_SPECIAL_COMPETITION_LIST_RU;
		}
		
		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				prepStatement.setInt(1, idSport);
				prepStatement.setInt(2, idTournament);
				prepStatement.setInt(3, fromRecord);
				prepStatement.setInt(4, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder().buildId(resultSet.getInt(1))
								.buildStartTime(resultSet.getTimestamp(2));

						Sport sport = new SportBuilder().buildName(resultSet.getString(3)).build();
						Tournament tournament = new TournamentBuilder().buildName(resultSet.getString(4)).build();
						Club homeTeamClub = new ClubBuilder().buildName(resultSet.getString(5)).build();
						Team homeTeam = new TeamBuilder().buildClub(homeTeamClub).build();
						Club awayTeamClub = new ClubBuilder().buildName(resultSet.getString(6)).build();
						Team awayTeam = new TeamBuilder().buildClub(awayTeamClub).build();

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildName(resultSet.getString(7)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(8))
								.buildDrawRate(resultSet.getBigDecimal(9))
								.buildWinAwayRate(resultSet.getBigDecimal(10));

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
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
		return competitionList;
	}

	@Override
	public int obtainSpecialCompetitionListCount(int idSport, int idTournament, boolean isPreparedOnly)
			throws DAOException {
		
		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

		SQLName sqlName = isPreparedOnly ? SQLName.GET_PREPARED_SPECIAL_COMPETITION_LIST_COUNT
				: SQLName.GET_SPECIAL_COMPETITION_LIST_COUNT;
		int count = 0;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, idSport);
				prepStatement.setInt(2, idTournament);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
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
		return count;
	}

	@Override
	public int obtainCompetitionResultListCount() throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		
		SQLName sqlName = SQLName.GET_COMPETITION_RESULT_LIST_COUNT;
		int count = 0;

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
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
		return count;
	}

	@Override
	public List<Competition> obtainCompetitionResultList(int fromRecord, int recordCount, Locale locale)
			throws DAOException {

		IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		
		SQLName sqlName = locale.equals(Locale.ENGLISH) ? SQLName.GET_RESULT_COMPETITION_LIST_EN
				: SQLName.GET_RESULT_COMPETITION_LIST_RU;
		
		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = connectionPool.getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(SQLProvider.getInstance().getSql(sqlName))) {
				prepStatement.setInt(1, fromRecord);
				prepStatement.setInt(2, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder().buildId(resultSet.getInt(1))
								.buildStartTime(resultSet.getTimestamp(2));

						Sport sport = new SportBuilder().buildName(resultSet.getString(3)).build();
						Tournament tournament = new TournamentBuilder().buildName(resultSet.getString(4)).build();
						Club homeTeamClub = new ClubBuilder().buildName(resultSet.getString(5)).build();
						Team homeTeam = new TeamBuilder().buildClub(homeTeamClub).build();
						Club awayTeamClub = new ClubBuilder().buildName(resultSet.getString(6)).build();
						Team awayTeam = new TeamBuilder().buildClub(awayTeamClub).build();

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildName(resultSet.getString(7)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(8))
								.buildDrawRate(resultSet.getBigDecimal(9)).buildWinAwayRate(resultSet.getBigDecimal(10))
								.buildResult(EventType.getTypeByShortName(resultSet.getString(11)));

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
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
		return competitionList;
	}
}
