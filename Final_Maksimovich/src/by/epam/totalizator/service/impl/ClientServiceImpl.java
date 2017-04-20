package by.epam.totalizator.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.IClientService;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * #IClientService interface implementation for the Betting entity {@link User}
 */
public class ClientServiceImpl implements IClientService {

	private static final String CLIENT_SERVICE_EXCEPTION_MESSAGE = "ClientService problem";

	/**
	 * key for the localized message
	 */
	private static final String EMAIL_IN_USE_ERROR_KEY = "local.validation.error.is_in_use";

	/**
	 * Method checks the input parameters performs a dao level call to get user
	 * data by login and password
	 * 
	 * @param login
	 * @param password
	 * @return instance of {@link User}
	 * 
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public User signIn(String login, String password) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateLogin(login);
		validationService.validatePassword(password);

		DAOFactory factory = DAOFactory.getInstance();
		User user;

		try {
			user = factory.getUserDAO().signIn(login, password);

			if (user == null) {
				throw new ServiceValidationException("Wrong login/password");
			}

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return user;
	}

	/**
	 * Method checks the input parameters performs a dao level call to add a new
	 * user
	 * 
	 * @param user
	 *            bean object {@link User}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void registration(User user) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateUser(user);

		try {
			DAOFactory factory = DAOFactory.getInstance();
			factory.getUserDAO().registration(user);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}

	}

	/**
	 * Method checks the input parameters performs a dao level call to check
	 * whether there is a user with such login
	 * 
	 * @param login
	 * @param locale
	 *            for a localized query to the database
	 * @return true, if he was found
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void checkLogin(String login, Locale locale) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateLogin(login);

		DAOFactory factory = DAOFactory.getInstance();
		boolean isLoginReserved = false;

		try {
			isLoginReserved = factory.getUserDAO().isLoginReserved(login);

			if (isLoginReserved) {
				String message = LocalizationBundle.getProperty(locale, EMAIL_IN_USE_ERROR_KEY);
				throw new ServiceValidationException(message);
			}

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Method checks the input parameters performs a dao level call to get User
	 * entity by its id
	 * 
	 * @param IdUser
	 * @param locale
	 *            for a localized query to the database
	 * @return instance of {@link User}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public User getUserProfile(int IdUser, Locale locale) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(IdUser);

		DAOFactory factory = DAOFactory.getInstance();
		User user;

		try {
			user = factory.getUserDAO().getUserProfile(IdUser, locale);

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return user;
	}

	/**
	 * Method checks the input parameters performs a dao level call to get a
	 * list of users
	 * 
	 * @param pageNumber
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * @return {@link java.util.List} instances of {@link User}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public List<User> obtainUserList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {
		
		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();
		List<User> userList;

		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			userList = factory.getUserDAO()
					.obtainUserList(fromRecord, recordQuantityPerPage, locale);
			
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return userList;
	}

	/**
	 * Method performs a dao level call to get the total number of users
	 * 
	 * @return number of entries
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 */
	@Override
	public int obtainAllUserCount() throws ServiceException {

		DAOFactory factory = DAOFactory.getInstance();
		int count;

		try {
			count = factory.getUserDAO().obtainAllUserCount();

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

	/**
	 * Method checks the input parameters performs a dao level call to ban the
	 * user by its id
	 * 
	 * @param idUser
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void banUser(int idUser) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().banUser(idUser);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Method checks the input parameters performs a dao level call to unban the
	 * user by its id
	 * 
	 * @param idUser
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void unbanUser(int idUser) throws ServiceException, ServiceValidationException {
		
		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().unbanUser(idUser);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Method checks the input parameters performs a dao level call to change
	 * the user role by its id
	 * 
	 * @param idUser
	 * @param userType
	 *            instance of {@link UserType}
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void changeUserType(int idUser, UserType userType) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().changeUserType(idUser, userType);

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	/**
	 * Method checks the input parameters performs a dao level call to change
	 * the user's balance
	 * 
	 * @param idUser
	 * @param amount
	 *            value for increase
	 * @throws ServiceException
	 *             when catched error from dao {@link DAOException} wraps it up
	 * @throws ServiceValidationException
	 *             appears when input parameter are invalid
	 *             {@link IValidationService}
	 */
	@Override
	public void changeUserBalance(int idUser, BigDecimal amount) throws ServiceException, ServiceValidationException {

		IValidationService validationService = 
				ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);
		validationService.validateAmount(amount);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().changeUserBalance(idUser, amount);

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}
}