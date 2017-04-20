package by.epam.totalizator.service.impl;

import by.epam.totalizator.service.IBettingService;
import by.epam.totalizator.service.IValidationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * #IBettingService interface implementation for the Betting entity
 * {@link Betting}
 */
public class BettingServiceImpl implements IBettingService {

	private static final String BETTING_SERVICE_EXCEPTION_MESSAGE = "BettingService problem";

	/**
	 * key for the localized message
	 */
	private static final String WRONG_BET_SIZE_ERROR_KEY = "local.validation.error.bet_size";

	/**
	 * Method checks the input parameters and the possibility of the betting,
	 * performs a dao level call to make betting.
	 * 
	 * @param betting
	 *            bean object {@link Betting}
	 * @param userBalance
	 *            the value of the current user balance
	 * @param locale
	 * 		for a localized query to the database
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void makeBetting(Betting betting, BigDecimal userBalance, Locale locale)
			throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateBetting(betting);

		// the possibility of the betting checking
		if (betting.getBetSize().compareTo(userBalance) != -1) {
			String message = LocalizationBundle.getProperty(locale, WRONG_BET_SIZE_ERROR_KEY);
			throw new ServiceValidationException(message);
		}

		int idUser = betting.getUser().getId();

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getBettingDAO().makeBetting(betting);
			factory.getUserDAO().changeUserBalance(idUser, betting.getBetSize().negate());
		} catch (DAOException e) {
			throw new ServiceException(BETTING_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

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
	 * 		for a localized query to the database
	 * @return {@link java.util.List} instances of {@link Betting}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public List<Betting> obtainUserBettingList(int idUser, int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);
		validationService.validatePageNumber(pageNumber);
		validationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		List<Betting> bettingList;
		
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			bettingList = factory.getBettingDAO().obtainUserBettingList(idUser, fromRecord, recordQuantityPerPage,
					locale);

		} catch (DAOException e) {
			throw new ServiceException(BETTING_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return bettingList;
	}

	/**
	 * Method checks the input parameters, performs a dao level call to receive
	 * the total number of betting of the user by its id
	 * 
	 * @param idUser
	 * @return number of entries
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public int obtainUserBettingListCount(int idUser) throws ServiceException, ServiceValidationException {
		int count;

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getBettingDAO().obtainUserBettingCount(idUser);
		} catch (DAOException e) {
			throw new ServiceException(BETTING_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}
}
