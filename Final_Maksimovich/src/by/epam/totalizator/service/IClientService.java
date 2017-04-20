package by.epam.totalizator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Service interface for the User entity {@link User}
 */
public interface IClientService {

	/**
	 * Method checks the input parameters performs a dao level call to get user
	 * data by login and password
	 * 
	 * @param login
	 * @param password
	 * @return instance of {@link User}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public User signIn(String login, String password) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters performs a dao level call to add a new
	 * user
	 * 
	 * @param user
	 *            bean object {@link User}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void registration(User user) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters performs a dao level call to check
	 * whether there is a user with such login
	 * 
	 * @param login
	 * @param locale
	 *            for a localized query to the database
	 * @return true, if he was found
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void checkLogin(String login, Locale locale) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters performs a dao level call to get User
	 * entity by its id
	 * 
	 * @param IdUser
	 * @param locale
	 *            for a localized query to the database
	 * @return instance of {@link User}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public User getUserProfile(int IdUser, Locale locale) throws ServiceException, ServiceValidationException;

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
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public List<User> obtainUserList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	/**
	 * Method performs a dao level call to get the total number of users
	 * 
	 * @return number of entries
	 * @throws ServiceException
	 *             if dao error was detected
	 */
	public int obtainAllUserCount() throws ServiceException;

	/**
	 * Method checks the input parameters performs a dao level call to ban the
	 * user by its id
	 * 
	 * @param idUser
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void banUser(int idUser) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters performs a dao level call to unban the
	 * user by its id
	 * 
	 * @param idUser
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void unbanUser(int idUser) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters performs a dao level call to change
	 * the user role by its id
	 * 
	 * @param idUser
	 * @param userType
	 *            instance of {@link UserType}
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void changeUserType(int idUser, UserType userType) throws ServiceException, ServiceValidationException;

	/**
	 * Method checks the input parameters performs a dao level call to change
	 * the user's balance
	 * 
	 * @param idUser
	 * @param amount
	 *            value for increase
	 * @throws ServiceException
	 *             if dao error was detected
	 * @throws ServiceValidationException
	 *             if validation error was detected
	 */
	public void changeUserBalance(int idUser, BigDecimal amount) throws ServiceException, ServiceValidationException;
}
