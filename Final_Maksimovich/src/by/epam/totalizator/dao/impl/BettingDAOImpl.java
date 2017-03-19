package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.bean.BetType;
import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Locale;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.dao.BettingDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.BettingSQLStore;
import by.epam.totalizator.util.build.BettingBuilder;
import by.epam.totalizator.util.build.ClubBuilder;
import by.epam.totalizator.util.build.CompetitionBuilder;
import by.epam.totalizator.util.build.SportBuilder;
import by.epam.totalizator.util.build.TeamBuilder;
import by.epam.totalizator.util.build.TournamentBuilder;
import by.epam.totalizator.util.build.UserBuilder;

public class BettingDAOImpl implements BettingDAO {

	@Override
	public void makeBetting(Betting betting) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Betting getBettingInfo(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBetting(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Betting> obtainUserBettingList(int userId, int fromRecord, int recordCount) throws DAOException {

		List<Betting> bettingList = new ArrayList<>(recordCount);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection.prepareStatement(BettingSQLStore.GET_USER_BETTING_LIST)) {
				prepStatement.setInt(1, userId);
				prepStatement.setInt(2, fromRecord);
				prepStatement.setInt(3, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						BettingBuilder bettingBuilder = new BettingBuilder().buildId(resultSet.getInt(1))
								.buildBetTime(resultSet.getTimestamp(2));

						Sport sport = new SportBuilder()
								.buildName(resultSet.getString(3))
								.build();
						Tournament tournament = new TournamentBuilder()
								.buildSport(sport)
								.buildName(resultSet.getString(4))
								.build();
						Club homeClub = new ClubBuilder()
											.buildName(resultSet.getString(5))
											.build();
						Team homeTeam = new TeamBuilder()
											.buildSport(sport)
											.buildClub(homeClub).build();
						Club awayClub = new ClubBuilder()
											.buildName(resultSet.getString(6))
											.build();
						Team awayTeam = new TeamBuilder()
											.buildSport(sport)
											.buildClub(awayClub)
											.build();
						Competition competition = new CompetitionBuilder()
								.buildTournament(tournament)
								.buildSport(sport)
								.buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam)
								.build();
						bettingBuilder
								.buildCompetition(competition)
								.buildBetType(BetType.getTypeByShortName(resultSet.getString(7)))
								.buildBetSize(resultSet.getBigDecimal(8))
								.buildBetRate(resultSet.getBigDecimal(9))
								.buildGain(resultSet.getBigDecimal(10));
						User user = new UserBuilder()
										.buildLocale(Locale.getLocaleByShortName(resultSet.getString(11)))
										.build();
								
						Betting betting = bettingBuilder
											.buildUser(user)
											.build();
						bettingList.add(betting);
					}
				}
			}

		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}

		return bettingList;

	}

	@Override
	public int obtainUserBettingCount(int userId) throws DAOException {

		int count = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(BettingSQLStore.GET_USER_BETTING_COUNT)) {

				prepStatement.setInt(1, userId);
				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {
						count = resultSet.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}
		return count;
	}

}
