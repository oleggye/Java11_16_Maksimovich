package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface CompetitionService {

	void addCompetition(Competition competition) throws ServiceException, ServiceValidationException;

	Competition obtainCompetition(int idCompetition, Locale locale) throws ServiceException, ServiceValidationException;

	void updateCompetition(Competition competition) throws ServiceException, ServiceValidationException;

	void updateComeptititonRate(Competition competition) throws ServiceException, ServiceValidationException;

	void deleteCompetition(int idCompetition) throws ServiceException, ServiceValidationException;

	List<Competition> obtainCompetitionList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	int obtainAllCompetitionCount() throws ServiceException, ServiceValidationException;

	List<Competition> obtainAvailableCompetitionList(boolean isClient, int pageNumber, int recordQuantityPerPage,
			Locale locale) throws ServiceException, ServiceValidationException;

	int obtainAvailableCompetitionListCount(boolean isClient) throws ServiceException, ServiceValidationException;

	List<Competition> obtainSpecialCompetitionList(boolean isClient, int idSport, int idTournament, int pageNumber,
			int recordQuantityPerPage, Locale locale) throws ServiceException, ServiceValidationException;

	int obtainSpecialCompetitionListCount(boolean isClient, int idSport, int idTournament)
			throws ServiceException, ServiceValidationException;

	List<Competition> obtainCompetitionResultList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	int obtainCompetitionResultListCount() throws ServiceException, ServiceValidationException;
}
