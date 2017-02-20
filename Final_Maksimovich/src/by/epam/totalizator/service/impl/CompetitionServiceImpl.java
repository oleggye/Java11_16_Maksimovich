package by.epam.totalizator.service.impl;

import java.util.LinkedList;
import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.CompetitionService;
import by.epam.totalizator.service.exception.ServiceException;

public class CompetitionServiceImpl implements CompetitionService {

	@Override
	public void addCompetition(Competition competition) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Competition getCompetition(int id) throws ServiceException {

		if (id < 1) {
			throw new ServiceException("Wrong param: id= " + id);
		}

		DAOFactory factory = DAOFactory.getInstance();
		Competition competition = null;

		try {
			competition = factory.getCompetitionDAO().getCompetition(id);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competition;
	}

	@Override
	public void updateCompetition(Competition competition) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCompetition(int id) throws ServiceException {

		if (id < 1) {
			throw new ServiceException("Wrong param: id= " + id);
		}

		DAOFactory factory = DAOFactory.getInstance();

		try {
			factory.getCompetitionDAO().deleteCompetition(id);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}

	}

	@Override
	public List<Competition> getAllCompetitions() throws ServiceException {

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitions;

		try {
			competitions = factory.getCompetitionDAO().getAllCompetitions();
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competitions;
	}

	// TODO:RESULT_COMPETITIONS!

	@Override
	public int getAllCompetitionsCount() throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAvailableCompetitionsCount() throws ServiceException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getCompetitionDAO().getAvailableCompetitionsCount();
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

	public List<Competition> getAvailableCompetitions(int pageNumber, int recordsPerPage) throws ServiceException {

		if (pageNumber < 1 || recordsPerPage < 1) {
			throw new ServiceException(
					"Wrong params: pageNumber= " + pageNumber + ", recordsPerPage= " + recordsPerPage);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitions;
		int fromRecord = (pageNumber - 1) * (recordsPerPage);
		int recordCount = recordsPerPage;

		try {
			competitions = factory.getCompetitionDAO().getAvailableCompetitions(fromRecord, recordCount);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competitions;
	}

	@Override
	public List<Competition> getSpecialCompetitions(int idSport, int idTournament) throws ServiceException {

		if (idSport < 1 || idTournament < 1) {
			throw new ServiceException("Wrong params: idSport= " + idSport + ", idTournament= " + idTournament);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Competition> competitions;

		try {
			competitions = factory.getCompetitionDAO().getSpecialCompetitions(idSport, idTournament);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return competitions;
	}
}
