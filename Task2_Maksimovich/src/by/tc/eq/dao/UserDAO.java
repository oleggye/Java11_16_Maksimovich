package by.tc.eq.dao;

import java.util.List;

import by.tc.eq.bean.User;
import by.tc.eq.dao.exeption.DAOException;

public interface UserDAO {

	public void addUser(User user) throws DAOException;

	public void updateUser(User user) throws DAOException;

	public void deleteUser(int id) throws DAOException;

	public int signInUser(String login, String password) throws DAOException;

	public User getUser(int id) throws DAOException;

	public List<User> getAllUsers() throws DAOException;

	public List<User> getAllDebtors() throws DAOException;

}
