package by.epam.totalizator.service;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface CompetitionService {

	public void addCompetition(Competition competition) throws ServiceException, ServiceValidationException;

	public void obtainCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;

	public void updateCompetition(Competition competition) throws ServiceException, ServiceValidationException;

	public void deleteCompetition(int id) throws ServiceException, ServiceValidationException;

	public void takeDataForCompetition(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;

	public void obtainCompetitionsList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;

	public void obtainAvailableCompetitionsList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;

	public void obtainSpecialCompetitionsList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;

	public void obtainCompetitionResultList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;
}
