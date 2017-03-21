package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.dao.exception.DAOException;

public interface TeamDAO {

	public List<Team> obtainTeamListByIdSport(int idSport) throws DAOException;
}
