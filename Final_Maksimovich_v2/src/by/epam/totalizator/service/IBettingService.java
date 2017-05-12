package by.epam.totalizator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Service interface for the Betting entity {@link Betting}
 */
public interface IBettingService {

	/**
	 * Method checks the input parameters and the possibility of the betting,
	 * performs a dao level call to make betting.
	 * 
	 * @param betting
	 *            bean object {@link Betting}
	 * @param userBalance
	 *            the value of the current user balance
	 * @param locale
	 *            for a localized query to the database
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void makeBetting(Betting betting, BigDecimal userBalance, Locale locale)
			throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters, performs a dao level call to receive
	 * a list of betting of the user by its id
	 * 
	 * @param idUser
	 * @param pageNumber
	 *            starting with the record number
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Betting}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             f validation error was detected
	 */
	public List<Betting> obtainUserBettingList(int idUser, int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters, performs a dao level call to receive
	 * the total number of betting of the user by its id
	 * 
	 * @param idUser
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             f validation error was detected
	 */
	public int obtainUserBettingListCount(int idUser) throws ServiceException, ServiceValidationException;
}
