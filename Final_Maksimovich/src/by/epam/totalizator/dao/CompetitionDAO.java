package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.dao.exception.DAOException;

public interface CompetitionDAO {

	public void addCompetition(Competition competition) throws DAOException;

	public Competition obtainCompetition(int id) throws DAOException;

	public void updateCompetition(Competition competition) throws DAOException;

	public void deleteCompetition(int id) throws DAOException;

	public int getAllCompetitionsCount() throws DAOException;

	public List<Competition> obtainCompetitionsList() throws DAOException;

	public int obtainAvailableCompetitionsCount() throws DAOException;

	public List<Competition> obtainAvailableCompetitionsList(int fromRecord, int recordCount) throws DAOException;

	public int obtainSpecialCompetitionsCount(int idSport, int idTournament) throws DAOException;

	public List<Competition> obtainSpecialCompetitionsList(int idSport, int idTournament, int fromRecord, int recordCount)
			throws DAOException;

	public int obtainCompetitionResultCount() throws DAOException;

	public List<Competition> obtainCompetitionResultList(int fromRecord, int recordCount) throws DAOException;

}
