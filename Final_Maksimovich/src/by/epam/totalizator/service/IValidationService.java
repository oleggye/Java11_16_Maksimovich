package by.epam.totalizator.service;

import java.math.BigDecimal;
import java.util.Date;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Service interface for validation
 */
public interface IValidationService {

	/**
	 * Method validates bean object of {@link User}
	 * 
	 * @param user
	 *            instance of {@link User}
	 * @throws ServiceValidationException
	 *             if bean object is invalid
	 */
	void validateUser(User user) throws ServiceValidationException;

	/**
	 * Method validates string with login param
	 * 
	 * @param login
	 * @throws ServiceValidationException
	 *             if it's invalid
	 */
	void validateLogin(String login) throws ServiceValidationException;

	/**
	 * Method checks string with password param
	 * 
	 * @param password
	 * @throws ServiceValidationException
	 *             if it's invalid
	 */
	void validatePassword(String password) throws ServiceValidationException;

	/**
	 * Method validates id value
	 * 
	 * @param idUser
	 * @throws ServiceValidationException
	 *             if the id param is invalid
	 */
	void validateIdUser(int idUser) throws ServiceValidationException;

	/**
	 * Method validates id value
	 * 
	 * @param idSport
	 * @throws ServiceValidationException
	 *             if the id param is invalid
	 */
	void validateIdSport(int idSport) throws ServiceValidationException;

	/**
	 * Method validates id value
	 * 
	 * @param idTournament
	 * @throws ServiceValidationException
	 *             if the id param is invalid
	 */
	void validateIdTournament(int idTournament) throws ServiceValidationException;

	/**
	 * Method validates id value
	 * 
	 * @param idCompetition
	 * @throws ServiceValidationException
	 *             if the id param is invalid
	 */
	void validateIdCompetition(int idCompetition) throws ServiceValidationException;

	/**
	 * Method validates id value
	 * 
	 * @param idClub
	 * @throws ServiceValidationException
	 *             if the id param is invalid
	 */
	void validateIdClub(int idClub) throws ServiceValidationException;

	/**
	 * Method validates id value
	 * 
	 * @param idCountry
	 * @throws ServiceValidationException
	 *             if the id param is invalid
	 */
	void validateIdCountry(int idCountry) throws ServiceValidationException;

	/**
	 * Method validates page number value
	 * 
	 * @param pageNumber
	 * @throws ServiceValidationException
	 *             if the pageNumber value is invalid
	 */
	void validatePageNumber(int pageNumber) throws ServiceValidationException;

	/**
	 * Method validates instance of {@link Date}
	 * 
	 * @param startTime
	 * @throws ServiceValidationException
	 *             if the startTime value is invalid
	 */
	void validateStartTime(Date startTime) throws ServiceValidationException;

	/**
	 * Method validates value of {@link BigDecimal} instance
	 * 
	 * @param homeRate
	 * @throws ServiceValidationException
	 *             if the homeRate value is invalid
	 */
	void validateHomeRate(BigDecimal homeRate) throws ServiceValidationException;

	/**
	 * Method validates value of {@link BigDecimal} instance
	 * 
	 * @param drawRate
	 * @throws ServiceValidationException
	 *             if the drawRate value is invalid
	 */
	void validateDrawRate(BigDecimal drawRate) throws ServiceValidationException;

	/**
	 * Method validates value of {@link BigDecimal} instance
	 * 
	 * @param awayRate
	 * @throws ServiceValidationException
	 *             if the awayRate value is invalid
	 */
	void validateAwayRate(BigDecimal awayRate) throws ServiceValidationException;

	/**
	 * Method validates value of {@link BigDecimal} instance
	 * 
	 * @param amount
	 * @throws ServiceValidationException
	 *             if the awayRate amount is invalid
	 */
	void validateAmount(BigDecimal amount) throws ServiceValidationException;

	/**
	 * Method validates object of {@link EventType}
	 * 
	 * @param eventType
	 *            instance of {@link EventType}
	 * @throws ServiceValidationException
	 *             if object is invalid
	 */
	void validateEventType(EventType eventType) throws ServiceValidationException;

	/**
	 * Method validates bean object of {@link Betting}
	 * 
	 * @param betting
	 *            instance of {@link Betting}
	 * @throws ServiceValidationException
	 *             if bean object is invalid
	 */
	void validateBetting(Betting betting) throws ServiceValidationException;
}