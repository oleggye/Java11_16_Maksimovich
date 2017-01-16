package by.tc.eq.service;

import by.tc.eq.bean.User;
import by.tc.eq.service.exception.ServiceException;

public interface ClientService {

	void registeration(User user) throws ServiceException;

	int singIn(String login, String password) throws ServiceException;

	void signOut() throws ServiceException;

	void updateUserData(User user) throws ServiceException;

	void deleteUser(int id) throws ServiceException;

	User getUserInfo(int id) throws ServiceException;

}
