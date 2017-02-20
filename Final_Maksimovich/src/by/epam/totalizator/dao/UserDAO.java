package by.epam.totalizator.dao;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.dao.exception.DAOException;

public interface UserDAO {

	public User signIn(String login, String password) throws DAOException;

	public void registration(User user) throws DAOException;

	public User getUserProfile(int id) throws DAOException;

	public void deleteUser(int id) throws DAOException;

	public void updateUser(User user) throws DAOException;
}
