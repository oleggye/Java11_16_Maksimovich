package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.CompetitionDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.ConnectorDB;
import by.epam.totalizator.dao.util.build.CompetitionBuilder;
import by.epam.totalizator.dao.util.sql.CompetitionSQLStrore;

public class CompetitionDAOImpl implements CompetitionDAO {

	private static final int DEFAULT_ID_VALUE = 0;

	@Override
	public void addCompetition(Competition competition) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Competition getCompetition(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
	public List<Competition> getAllCompetitions() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCompetitionsCount() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAvailableCompetitionsCount() throws DAOException {

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
			System.out.println(e);
			throw new DAOException("Can't execute query", e);
		}
		return count;
	}

	@Override
	public List<Competition> getAvailableCompetitions(int fromRecord, int recordCount) throws DAOException {

		List<Competition> competitions = new ArrayList<>(recordCount);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

			try (PreparedStatement prepStatement = connection
					.prepareStatement(CompetitionSQLStrore.GET_AVAILABLE_COMPETITIONS)) {
				prepStatement.setInt(1, fromRecord);
				prepStatement.setInt(2, recordCount);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					while (resultSet.next()) {

						CompetitionBuilder builder = new CompetitionBuilder();

						builder.buildId(resultSet.getInt(1));
						builder.buildStartTime(resultSet.getDate(2));
						builder.buildSport(DEFAULT_ID_VALUE, resultSet.getString(3));
						builder.buildTournament(DEFAULT_ID_VALUE, resultSet.getString(4), null);

						Club club = new Club();
						club.setName(resultSet.getString(5));
						builder.buildHomeTeam(club, null, null);
						club = new Club();
						club.setName(resultSet.getString(6));
						builder.buildAwayTeam(club, null, null);
						builder.buildCountry(DEFAULT_ID_VALUE, resultSet.getString(7));
						builder.builderWinHomeRate(resultSet.getBigDecimal(8));
						builder.buildDrawRate(resultSet.getBigDecimal(9));
						builder.buildWinAwayRate(resultSet.getBigDecimal(10));

						Competition competition = builder.getCompetition();
						competitions.add(competition);
					}
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException("Can't execute query", e);
		}

		return competitions;
	}

	@Override
	public List<Competition> getSpecialCompetitions(int idSport, int idTournament) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
