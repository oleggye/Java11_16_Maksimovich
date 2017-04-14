package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.dao.exception.DAOException;

public interface TournamentDAO {

	public List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale) throws DAOException;
}
