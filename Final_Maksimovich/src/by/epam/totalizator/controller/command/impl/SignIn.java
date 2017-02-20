package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SignIn implements Command {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_USERID = "userId";
	private static final String PARAM_NAME_USERTYPE = "userType";
	private static final String HOME_PAGE_URL = "path.page.home";
	private static final String SIGNIN_PAGE_URL = "path.page.signIn";
	private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

	@Override
	public String execute(HttpServletRequest request) {

		// TODO:временно
		String page = ConfigurationManager.getProperty(SIGNIN_PAGE_URL);

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);

		ServiceFactory factory = ServiceFactory.getInstance();
		
		try {

			User user = factory.getClientService().signIn(login, password);

			if (user.getId() != 0) {
				HttpSession session = request.getSession(true);
				session.setAttribute(PARAM_NAME_USERID, user.getId());
				session.setAttribute(PARAM_NAME_LOGIN, user.getEmail());
				session.setAttribute(PARAM_NAME_USERTYPE, user.getUserType());

				// page = ConfigurationManager.getProperty(HOME_PAGE_URL);
				page = "controller?command=home";

			}

			// привер: page =("home.jsp");
		} catch (ServiceException e) {
			// TODO: дописать обработчик!
			// пример: ("error-login.jsp");

			request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
		}
		return page;
	}

}
