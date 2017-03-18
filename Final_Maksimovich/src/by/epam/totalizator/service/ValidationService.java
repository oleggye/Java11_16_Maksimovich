package by.epam.totalizator.service;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface ValidationService {

	void validateUser(User user) throws ServiceValidationException;

	void validateLoginAndPassword(String login, String password) throws ServiceValidationException;

	void validateIdSportAndIdTournament(String idSportParam, String idTournamentParam)
			throws ServiceValidationException;

	public void validatePageNumber(String pageNumberParam) throws ServiceValidationException;

}
