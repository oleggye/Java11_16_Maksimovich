package by.epam.totalizator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface ClientService {

	public User signIn(String login, String password) throws ServiceException, ServiceValidationException;

	public void registration(User user) throws ServiceException, ServiceValidationException;

	public boolean checkLogin(String login) throws ServiceException, ServiceValidationException;

	public User getUserProfile(int IdUser, Locale locale) throws ServiceException, ServiceValidationException;

	public List<User> obtainUserList(int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	public int obtainAllUserCount() throws ServiceException;

	public void banUser(int idUser) throws ServiceException, ServiceValidationException;

	public void unbanUser(int idUser) throws ServiceException, ServiceValidationException;

	public void changeUserType(int idUser, UserType userType) throws ServiceException, ServiceValidationException;

	public void changeUserBalance(int idUser, BigDecimal balance) throws ServiceException, ServiceValidationException;
}
