package by.epam.totalizator.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.dao.exception.DAOException;

/**
 * Interface for the Data Access Object that the User entity uses {@link User}
 */
public interface UserDAO {

	public User signIn(String login, String password) throws DAOException;

	public void registration(User user) throws DAOException;

	public boolean checkLogin(String login) throws DAOException;

	public User getUserProfile(int id, Locale locale) throws DAOException;

	public List<User> obtainUserList(int fromRecord, int recordCount, Locale locale) throws DAOException;

	public int obtainAllUserCount() throws DAOException;

	public void banUser(int idUser) throws DAOException;

	public void unbanUser(int idUser) throws DAOException;

	public void changeUserType(int idUser, UserType userType) throws DAOException;

	public void changeUserBalance(int idUser, BigDecimal amount) throws DAOException;
}
