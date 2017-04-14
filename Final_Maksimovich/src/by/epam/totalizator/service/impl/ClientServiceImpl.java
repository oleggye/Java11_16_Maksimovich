package by.epam.totalizator.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ClientService;
import by.epam.totalizator.service.ValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class ClientServiceImpl implements ClientService {

	private static final String CLIENT_SERVICE_EXCEPTION_MESSAGE ="ClientService problem";

	@Override
	public User signIn(String login, String password) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ValidationService validationService = serviceFactory.getValidationService();
		// FIXME: раскомментировать
		// validationService.validateLogin(login);
		// validationService.validatePassword(password);

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

	@Override
	public void registration(User user) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateUser(user);

		try {
			DAOFactory factory = DAOFactory.getInstance();
			factory.getUserDAO().registration(user);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}

	}

	@Override
	public boolean checkLogin(String login) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ValidationService validationService = serviceFactory.getValidationService();
		validationService.validateLogin(login);

		DAOFactory factory = DAOFactory.getInstance();
		boolean isLoginReserved = false;

		try {
			isLoginReserved = factory.getUserDAO().checkLogin(login);

		} catch (DAOException e) {
			throw new ServiceException("Wrong login / password", e);
		}

		return isLoginReserved;
	}

	@Override
	public User getUserProfile(int IdUser, Locale locale) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdUser(IdUser);

		DAOFactory factory = DAOFactory.getInstance();
		User user;

		try {
			user = factory.getUserDAO().getUserProfile(IdUser, locale);

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return user;
	}

	@Override
	public List<User> obtainUserList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		DAOFactory factory = DAOFactory.getInstance();
		List<User> userList;

		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			userList = factory.getUserDAO().obtainUserList(fromRecord, recordQuantityPerPage, locale);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return userList;
	}

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

	@Override
	public void banUser(int idUser) throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().banUser(idUser);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void unbanUser(int idUser) throws ServiceException, ServiceValidationException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().unbanUser(idUser);
		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void changeUserType(int idUser, UserType userType) throws ServiceException, ServiceValidationException {

		ValidationService validatationService = ServiceFactory.getInstance().getValidationService();
		validatationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().changeUserType(idUser, userType);

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void changeUserBalance(int idUser, BigDecimal amount) throws ServiceException, ServiceValidationException {

		ValidationService validatationService = ServiceFactory.getInstance().getValidationService();
		validatationService.validateIdUser(idUser);
		validatationService.validateAmount(amount);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getUserDAO().changeUserBalance(idUser, amount);

		} catch (DAOException e) {
			throw new ServiceException(CLIENT_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}
}
