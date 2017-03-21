package by.epam.totalizator.service.impl;

import java.util.Date;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.ClientService;
import by.epam.totalizator.service.ValidationService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;
import by.epam.totalizator.util.build.UserBuilder;

public class ClientServiceImpl implements ClientService {

	private static final String PARAM_NAME_USERID = "userId";
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_EMAIL = "email";
	
	private static final String PARAM_NAME_FIRST_NAME = "firstName";
	private static final String PARAM_NAME_LAST_NAME = "lastName";
	private static final String PARAM_NAME_COUNTRY = "country";
	private static final String PARAM_NAME_PHONE_CODE = "code";
	private static final String PARAM_NAME_PHONE_NUMBER = "phone";
	private static final String PARAM_NAME_CURRENCY = "currency";
	private static final String PARAM_NAME_DATE = "date";
	private static final String PARAM_SECRET_QUESTION = "secquestion";
	private static final String PARAM_SECRET_ANSWER = "secanswer";

	private static final String ATTRIBUTE_NAME_USERID = "userId";
	private static final String ATTRIBUTE_NAME_LOGIN = "login";
	private static final String ATTRIBUTE_NAME_USERTYPE = "userType";
	private static final String ATTRIBUTE_NAME_BALANCE = "balance";
	private static final String ATTRIBUTE_NAME_LOCALE = "local";
	private static final String ATTRIBUTE_NAME_USER = "user";
	

	@Override
	public void signIn(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException {

		String login = requestContent.getRequestParam(PARAM_NAME_LOGIN);
		String password = requestContent.getRequestParam(PARAM_NAME_PASSWORD);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ValidationService validationService = serviceFactory.getValidationService();
		validationService.validateLoginAndPassword(login, password);

		DAOFactory factory = DAOFactory.getInstance();
		User user;

		try {
			user = factory.getUserDAO().signIn(login, password);

			if (user == null) {
				throw new ServiceValidationException("Wrong login/password");
			}

			requestContent.putSessionAttribute(ATTRIBUTE_NAME_USERID, user.getId());
			requestContent.putSessionAttribute(ATTRIBUTE_NAME_LOGIN, user.getEmail());
			requestContent.putSessionAttribute(ATTRIBUTE_NAME_BALANCE, user.getBalance());
			requestContent.putSessionAttribute(ATTRIBUTE_NAME_USERTYPE, user.getUserType());
			requestContent.putSessionAttribute(ATTRIBUTE_NAME_LOCALE, user.getLocale().toString());

		} catch (DAOException e) {
			throw new ServiceException("Client service problem", e);
		}
	}

	@Override
	public void registration(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException {
		
		UserBuilder userBuilder = new UserBuilder().buildFirstName(requestContent.getRequestParam(PARAM_NAME_FIRST_NAME))
				.buildLastName(requestContent.getRequestParam(PARAM_NAME_LAST_NAME))
				.buildEmail(requestContent.getRequestParam(PARAM_NAME_EMAIL));
				
		Country country = new Country();
		country.setName(requestContent.getRequestParam(PARAM_NAME_COUNTRY));
		
		Phone phone = new Phone();
		phone.setCode(requestContent.getRequestParam(PARAM_NAME_PHONE_CODE));
		phone.setPhoneNumber(requestContent.getRequestParam(PARAM_NAME_PHONE_NUMBER));
		
		User user = userBuilder.buildCountry(country)
							   .buildPhone(phone)
							   .buildCurrency(requestContent.getRequestParam(PARAM_NAME_CURRENCY))
							   .buildDateOfBirth(new Date(requestContent.getRequestParam(PARAM_NAME_DATE)))
							   .buildPassword(requestContent.getRequestParam(PARAM_NAME_PASSWORD))
							   .buildSecredQuestionId(Integer.valueOf(requestContent.getRequestParam(PARAM_SECRET_QUESTION)))
							   .buildSecredQuestionAnswer(requestContent.getRequestParam(PARAM_SECRET_ANSWER))
							   .build();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ValidationService validationService = serviceFactory.getValidationService();
		validationService.validateUser(user);

		try {
			DAOFactory factory = DAOFactory.getInstance();
			factory.getUserDAO().registration(user);
		} catch (DAOException e) {
			throw new ServiceException("Client service problem", e);
		}

	}

	@Override
	public boolean checkLogin(String login) throws ServiceException, ServiceValidationException {

		// TODO: VALIDATION!!!

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
	public void getUserProfile(SessionRequestContent requestContent) throws ServiceException {

		int userId = (Integer) requestContent.getSessionParam(PARAM_NAME_USERID);
		DAOFactory factory = DAOFactory.getInstance();

		try {
			User user = factory.getUserDAO().getUserProfile(userId);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_USER, user);

		} catch (DAOException e) {
			throw new ServiceException("Client service problem", e);
		}
	}

	@Override
	public void deleteUser(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}
}
