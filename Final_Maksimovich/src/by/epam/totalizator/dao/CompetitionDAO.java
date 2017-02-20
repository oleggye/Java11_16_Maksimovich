package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.dao.exception.DAOException;

public interface CompetitionDAO {

	public void addCompetition(Competition competition) throws DAOException;

	public Competition getCompetition(int id) throws DAOException;

	public void updateCompetition(Competition competition) throws DAOException;

	public void deleteCompetition(int id) throws DAOException;
	
	public int getAllCompetitionsCount()throws DAOException;

	public List<Competition> getAllCompetitions() throws DAOException;

	public int getAvailableCompetitionsCount() throws DAOException;

	public List<Competition> getAvailableCompetitions(int fromRecord, int recordCount) throws DAOException;

	public List<Competition> getSpecialCompetitions(int idSport, int idTournament) throws DAOException;

}
