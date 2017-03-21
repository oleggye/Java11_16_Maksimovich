package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.dao.TeamDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.CountrySQLStore;
import by.epam.totalizator.dao.util.sql.TeamSQLStore;
import by.epam.totalizator.util.build.ClubBuilder;
import by.epam.totalizator.util.build.CountryBuilder;
import by.epam.totalizator.util.build.TeamBuilder;

public class TeamDAOImpl implements TeamDAO {

	@Override
	public List<Team> obtainTeamListByIdSport(int idSport) throws DAOException {

		List<Team> teamList = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement prepStatement = connection
					.prepareStatement(TeamSQLStore.GET_TEAM_LIST_BY_ID_SPORT)) {
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
			throw new DAOException("Can't execute query", e);
		}
		return teamList;
	}

}
