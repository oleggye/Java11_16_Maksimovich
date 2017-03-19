package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Result;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.CompetitionDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.CompetitionSQLStrore;
import by.epam.totalizator.util.build.ClubBuilder;
import by.epam.totalizator.util.build.CompetitionBuilder;
import by.epam.totalizator.util.build.CountryBuilder;
import by.epam.totalizator.util.build.SportBuilder;
import by.epam.totalizator.util.build.TeamBuilder;
import by.epam.totalizator.util.build.TournamentBuilder;

public class CompetitionDAOImpl implements CompetitionDAO {

	@Override
	public void addCompetition(Competition competition) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Competition obtainCompetition(int id) throws DAOException {

		Competition competition = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_COMPETITION_BY_ID)) {
				prepStatement.setInt(1, id);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					if (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder()
																.buildStartTime(resultSet.getTimestamp(1));

						Sport sport = new SportBuilder().buildName(resultSet.getString(2)).build();
						Tournament tournament = new TournamentBuilder().buildName(resultSet.getString(3)).build();
						Club homeTeamClub = new ClubBuilder().buildName(resultSet.getString(4)).build();
						Team homeTeam = new TeamBuilder().buildClub(homeTeamClub).build();
						Club awayTeamClub = new ClubBuilder().buildName(resultSet.getString(5)).build();
						Team awayTeam = new TeamBuilder().buildClub(awayTeamClub).build();

						competitionBuilder.buildSport(sport)
										  .buildTournament(tournament)
										  .buildHomeTeam(homeTeam)
										  .buildAwayTeam(awayTeam);
					
						Country country = new CountryBuilder()
													.buildName(resultSet.getString(6))
													.build();
						
						competitionBuilder.buildCountry(country)
										  .builderWinHomeRate(resultSet.getBigDecimal(7))
										  .buildDrawRate(resultSet.getBigDecimal(8))
										  .buildWinAwayRate(resultSet.getBigDecimal(9));

						competition = competitionBuilder.build();
					}
				}
			}

		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}

		return competition;
	}

	@Override
	public void updateCompetition(Competition competition) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCompetition(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Competition> obtainCompetitionsList() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCompetitionsCount() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int obtainAvailableCompetitionsCount() throws DAOException {

		int count = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_AVAILABLE_COMPETITIONS_COUNT)) {

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

	@Override
	public List<Competition> obtainAvailableCompetitionsList(int fromRecord, int recordCount) throws DAOException {

		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_AVAILABLE_COMPETITION_LIST)) {
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

						competitionBuilder.buildSport(sport)
										  .buildTournament(tournament)
										  .buildHomeTeam(homeTeam)
										  .buildAwayTeam(awayTeam);
						
						Country country = new CountryBuilder()
												.buildName(resultSet.getString(7))
												.build();
						
						competitionBuilder.buildCountry(country)
										  .builderWinHomeRate(resultSet.getBigDecimal(8))
										  .buildDrawRate(resultSet.getBigDecimal(9))
										  .buildWinAwayRate(resultSet.getBigDecimal(10));

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
					}
				}
			}

		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}

		return competitionList;
	}

	@Override
	public List<Competition> obtainSpecialCompetitionsList(int idSport, int idTournament, int fromRecord,
			int recordCount) throws DAOException {
		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_SPECIAL_COMPETITION_LIST)) {
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

						competitionBuilder.buildSport(sport)
										  .buildTournament(tournament)
										  .buildHomeTeam(homeTeam)
										  .buildAwayTeam(awayTeam);
						
						Country country = new CountryBuilder()
													.buildName(resultSet.getString(7))
													.build();
						
						competitionBuilder.buildCountry(country)
								.builderWinHomeRate(resultSet.getBigDecimal(8))
								.buildDrawRate(resultSet.getBigDecimal(9))
								.buildWinAwayRate(resultSet.getBigDecimal(10));

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
					}
				}
			}

		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}

		return competitionList;
	}

	@Override
	public int obtainSpecialCompetitionsCount(int idSport, int idTournament) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int obtainCompetitionResultCount() throws DAOException {
		int count = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_COMPETITIONS_RESULT_COUNT)) {

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

	@Override
	public List<Competition> obtainCompetitionResultList(int fromRecord, int recordCount) throws DAOException {

		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_RESULT_COMPETITION_LIST)) {
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

						competitionBuilder.buildSport(sport)
										  .buildTournament(tournament)
										  .buildHomeTeam(homeTeam)
										  .buildAwayTeam(awayTeam);
						
						Country country = new CountryBuilder()
												.buildName(resultSet.getString(7))
												.build();
						
						competitionBuilder.buildCountry(country)
								.builderWinHomeRate(resultSet.getBigDecimal(8))
								.buildDrawRate(resultSet.getBigDecimal(9)).buildWinAwayRate(resultSet.getBigDecimal(10))
								.buildResult(Result.getTypeByShortName(resultSet.getString(11)));

						Competition competition = competitionBuilder.build();
						competitionList.add(competition);
					}
				}
			}

		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}

		return competitionList;
	}
}
