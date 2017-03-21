package by.epam.totalizator.service;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface ValidationService {

	void validateUser(User user) throws ServiceValidationException;

	void validateLoginAndPassword(String login, String password) throws ServiceValidationException;

	void validateIdSport(String idSportParam) throws ServiceValidationException;

	void validateIdTournament(String idTournamentParam) throws ServiceValidationException;

	void validateIdCompetition(String idCompetitionParam) throws ServiceValidationException;

	public void validatePageNumber(String pageNumberParam) throws ServiceValidationException;

	public void validateStartTime(String startTimeParam) throws ServiceValidationException;

	public void validateIdClubParam(String idClubParam) throws ServiceValidationException;

	public void validateIdCountryParam(String idCountryParam) throws ServiceValidationException;
}
