package by.epam.totalizator.service;

import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.service.exception.ServiceException;

public interface CompetitionService {

	public void addCompetition(Competition competition) throws ServiceException;

	public Competition getCompetition(int id) throws ServiceException;

	public void updateCompetition(Competition competition) throws ServiceException;

	public void deleteCompetition(int id) throws ServiceException;

	public int getAllCompetitionsCount() throws ServiceException;

	public List<Competition> getAllCompetitions() throws ServiceException;

	public int getAvailableCompetitionsCount() throws ServiceException;

	public List<Competition> getAvailableCompetitions(int pageNumber, int recordsPerPage) throws ServiceException;

	public List<Competition> getSpecialCompetitions(int idSport, int idTournament) throws ServiceException;

}
