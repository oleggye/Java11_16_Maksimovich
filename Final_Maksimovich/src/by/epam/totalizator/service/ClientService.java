package by.epam.totalizator.service;

import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface ClientService {

	public void signIn(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException;

	public void registration(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException;

	public boolean checkLogin(String login) throws ServiceException, ServiceValidationException;

	public void getUserProfile(SessionRequestContent requestContent) throws ServiceException;

	public void deleteUser(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException;

	public void updateUser(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException;

}
