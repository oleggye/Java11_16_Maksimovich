package by.epam.totalizator.dao.impl;

import java.math.BigDecimal;
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
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.ADD_COMPETITION)) {
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
			throw new DAOException("Can't execute query", e);
		}
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

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildName(resultSet.getString(6)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(7))
								.buildDrawRate(resultSet.getBigDecimal(8)).buildWinAwayRate(resultSet.getBigDecimal(9));

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
	public void updateAllCompetitionRate(BigDecimal homeRate, BigDecimal drawRate, BigDecimal awayRate)
			throws DAOException{
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.UPDATE_All_COMPETITION_RATE)) {
				prepStatement.setBigDecimal(1, homeRate);
				prepStatement.setBigDecimal(2, drawRate);
				prepStatement.setBigDecimal(3, awayRate);
				
				prepStatement.execute();
				
			}
		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}
	}

	@Override
	public void deleteCompetition(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Competition> obtainCompetitionList(int fromRecord, int recordCount) throws DAOException {
		
		List<Competition> competitionList = new ArrayList<>(recordCount);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_COMPETITION_LIST)) {
				prepStatement.setInt(1, fromRecord);
				prepStatement.setInt(2, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						CompetitionBuilder competitionBuilder = new CompetitionBuilder()
																		.buildId(resultSet.getInt(1))
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
								.buildDrawRate(resultSet.getBigDecimal(9)).buildWinAwayRate(resultSet.getBigDecimal(10));
						
						String resultParam = resultSet.getString(11);
						
						if(resultParam != null){
							competitionBuilder.buildResult(Result.getTypeByShortName(resultParam));
						}

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
	public int obtainCompetitionListCount() throws DAOException {
		
		int count = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_COMPETITION_LIST_COUNT)) {

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
	public int obtainAvailableCompetitionListCount() throws DAOException {

		int count = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_AVAILABLE_COMPETITION_LIST_COUNT)) {

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
	public List<Competition> obtainAvailableCompetitionList(int fromRecord, int recordCount) throws DAOException {

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
			throw new DAOException("Can't execute query", e);
		}

		return competitionList;
	}

	@Override
	public List<Competition> obtainSpecialCompetitionList(int idSport, int idTournament, int fromRecord,
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
			throw new DAOException("Can't execute query", e);
		}

		return competitionList;
	}

	@Override
	public int obtainSpecialCompetitionListCount(int idSport, int idTournament) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int obtainCompetitionResultListCount() throws DAOException {
		int count = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_COMPETITION_RESULT_LIST_COUNT)) {

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

						competitionBuilder.buildSport(sport).buildTournament(tournament).buildHomeTeam(homeTeam)
								.buildAwayTeam(awayTeam);

						Country country = new CountryBuilder().buildName(resultSet.getString(7)).build();

						competitionBuilder.buildCountry(country).builderWinHomeRate(resultSet.getBigDecimal(8))
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
