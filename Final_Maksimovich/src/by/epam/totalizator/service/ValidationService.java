package by.epam.totalizator.service;

import java.math.BigDecimal;
import java.util.Date;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface ValidationService {

	void validateUser(User user) throws ServiceValidationException;

	void validateLogin(String login) throws ServiceValidationException;

	void validatePassword(String password) throws ServiceValidationException;

	void validateIdUser(int idUser) throws ServiceValidationException;

	void validateIdSport(int idSport) throws ServiceValidationException;

	void validateIdTournament(int idTournament) throws ServiceValidationException;

	void validateIdCompetition(int idCompetition) throws ServiceValidationException;

	void validatePageNumber(int pageNumber) throws ServiceValidationException;

	void validateStartTime(Date startTime) throws ServiceValidationException;

	void validateIdClub(int idClub) throws ServiceValidationException;

	void validateIdCountry(int idCountry) throws ServiceValidationException;

	void validateHomeRate(BigDecimal homeRate) throws ServiceValidationException;

	void validateDrawRate(BigDecimal drawRate) throws ServiceValidationException;

	void validateAwayRate(BigDecimal awayRate) throws ServiceValidationException;

	void validateEventType(EventType eventType) throws ServiceValidationException;

	void validateBetting(Betting betting) throws ServiceValidationException;
}
