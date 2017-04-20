package by.epam.totalizator.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the User entity uses {@link User}
 */
public interface IUserDAO {

	/**
	 * Method tries to get user data by login and password
	 * 
	 * @param login
	 * @param password
	 * @return instance of {@link User}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public User signIn(String login, String password) throws DAOException;

	/**
	 * Method adds a new user to the db
	 * 
	 * @param user
	 *            bean object {@link User}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void registration(User user) throws DAOException;

	/**
	 * Method checks whether there is a user in the database with such login
	 * 
	 * @param login
	 * @return true, if he was found
	 * @throws DAOException
	 *             if database error was detected
	 */
	public boolean isLoginReserved(String login) throws DAOException;

	/**
	 * Method receives User entity by its id
	 * 
	 * @param idUser
	 * @param locale
	 *            for a localized query to the database
	 * @return instance of {@link User}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public User getUserProfile(int idUser, Locale locale) throws DAOException;

	/**
	 * Method receives a list of users
	 * 
	 * @param fromRecord
	 *            starting with the record number
	 * @param recordQuantityPerPage
	 *            requested number of entries
	 * @param locale
	 *            for a localized query to the database
	 * 
	 * @return {@link java.util.List} instances of {@link User}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public List<User> obtainUserList(int fromRecord, int recordQuantityPerPage, Locale locale) throws DAOException;

	/**
	 * Method receives the total number of users
	 * 
	 * @return number of entries
	 * @throws DAOException
	 *             if database error was detected
	 */
	public int obtainAllUserCount() throws DAOException;

	/**
	 * Method bans the user by its id
	 * 
	 * @param idUser
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void banUser(int idUser) throws DAOException;

	/**
	 * Method unbans the user by its id
	 * 
	 * @param idUser
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void unbanUser(int idUser) throws DAOException;

	/**
	 * Method changes the user role by its id
	 * 
	 * @param idUser
	 * @param userType
	 *            instance of {@link UserType}
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void changeUserType(int idUser, UserType userType) throws DAOException;

	/**
	 * Method changes the user's balance
	 * 
	 * @param idUser
	 * @param amount
	 *            value for increase
	 * 
	 * @throws DAOException
	 *             if database error was detected
	 */
	public void changeUserBalance(int idUser, BigDecimal amount) throws DAOException;
}
