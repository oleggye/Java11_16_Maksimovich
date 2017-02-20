package by.epam.totalizator.service.impl;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ClientService;
import by.epam.totalizator.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {

	@Override
	public User signIn(String login, String password) throws ServiceException {

		if (login == null || password == null) {
			return new User();
		}

		DAOFactory factory = DAOFactory.getInstance();
		User user;

		try {
			user = factory.getUserDAO().signIn(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Wrong login/ password", e);
		}

		return user;
	}

	@Override
	public void registration(User user) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserProfile(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
