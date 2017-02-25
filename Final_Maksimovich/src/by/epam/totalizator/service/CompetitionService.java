package by.epam.totalizator.service;

import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.service.exception.ServiceException;

public interface CompetitionService {

	public void addCompetition(Competition competition) throws ServiceException;

	public Competition obtainCompetition(int id) throws ServiceException;

	public void updateCompetition(Competition competition) throws ServiceException;

	public void deleteCompetition(int id) throws ServiceException;

	public int obtainAllCompetitionsCount() throws ServiceException;

	public List<Competition> obtainCompetitionsList() throws ServiceException;

	public int obtainAvailableCompetitionsCount() throws ServiceException;

	public List<Competition> obtainAvailableCompetitionsList(int pageNumber, int recordsPerPage)
			throws ServiceException;

	public int obtainSpecialCompetitionsCount(int idSport, int idTournament) throws ServiceException;

	public List<Competition> obtainSpecialCompetitionsList(int idSport, int idTournament, int pageNumber,
			int recordsPerPage) throws ServiceException;

	public int obtainCompetitionResultCount() throws ServiceException;

	public List<Competition> obtainCompetitionResultList(int pageNumber, int recordsPerPage) throws ServiceException;
}
