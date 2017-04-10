package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.dao.exception.DAOException;

public interface CompetitionDAO {

	public void addCompetition(Competition competition) throws DAOException;

	public Competition obtainCompetition(int id, Locale locale) throws DAOException;

	public void updateCompetition(Competition competition) throws DAOException;

	public void updateAllCompetitionRate(Competition competition) throws DAOException;

	public void deleteCompetition(int id) throws DAOException;

	public int obtainCompetitionListCount() throws DAOException;

	public List<Competition> obtainCompetitionList(int fromRecord, int recordCount, Locale locale) throws DAOException;

	public int obtainAvailableCompetitionListCount(boolean isPrepared) throws DAOException;

	public List<Competition> obtainAvailableCompetitionList(int fromRecord, int recordCount, boolean isPrepared,
			Locale locale) throws DAOException;

	public int obtainSpecialCompetitionListCount(int idSport, int idTournament, boolean isPrepared) throws DAOException;

	public List<Competition> obtainSpecialCompetitionList(int idSport, int idTournament, int fromRecord,
			int recordCount, boolean isPrepared, Locale locale) throws DAOException;

	public int obtainCompetitionResultListCount() throws DAOException;

	public List<Competition> obtainCompetitionResultList(int fromRecord, int recordCount, Locale locale)
			throws DAOException;

}
