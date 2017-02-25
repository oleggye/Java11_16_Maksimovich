package by.epam.totalizator.service;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.service.exception.ServiceException;

public interface ClientService {

	public User signIn(String login, String password) throws ServiceException;

	public void registration(User user) throws ServiceException;

	public User getUserProfile(int id) throws ServiceException;

	public void deleteUser(int id) throws ServiceException;
	
	public void updateUser(User user) throws ServiceException;

}
