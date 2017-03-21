package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.TournamentDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.util.ConnectionFactory;
import by.epam.totalizator.dao.util.sql.TournamentSQLStore;

public class TournamentDAOImpl implements TournamentDAO {

	@Override
	public List<Tournament> obtainTournamentListByIdSport(int idSport) throws DAOException {

		List<Tournament> tournamentList = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement prepStatement = connection
					.prepareStatement(TournamentSQLStore.GET_TOURNAMENT_LIST_BY_ID_SPORT)) {
				prepStatement.setInt(1, idSport);

				try (ResultSet resultSet = prepStatement.executeQuery()) {

					Tournament tournament = null;
					while (resultSet.next()) {

						tournament = new Tournament();
						tournament.setId(resultSet.getInt(1));
						tournament.setName(resultSet.getString(2));
						tournamentList.add(tournament);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Can't execute query", e);
		}
		return tournamentList;
	}
}
