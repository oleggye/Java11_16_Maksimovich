package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.dao.exception.DAOException;

public interface TeamDAO {

	public List<Team> obtainTeamListByIdSport(int idSport, Locale locale) throws DAOException;
}
