package by.epam.totalizator.controller.command.impl;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SignUp implements Command {

	private static final String PARAM_NAME_EMAIL = "email";
	private final String SIGN_UP_PAGE_URL = "path.page.signUp";
	private final String HOME_PAGE_URL = "path.page.home";
	private static final String ATTRIBUTE_ERROR_LOGIN_MESSAGE = "errorLoginMessage";
	private static final String ATTRIBUTE_ERROR_MESSAGE_SET = "errorMessageSet";

	@Override
	public String execute(HttpServletRequest request) {

		// TODO: временно!
		String page = null;

		String login = request.getParameter(PARAM_NAME_EMAIL);

		if (login != null) {

			ServiceFactory factory = ServiceFactory.getInstance();

			try {
				boolean isLoginReserved = factory.getClientService().checkLogin(login);

				if (isLoginReserved) {
					request.setAttribute(ATTRIBUTE_ERROR_LOGIN_MESSAGE, "login is reserved");

				} else {
					// TODO: нужно оценить надобность такой записи
					page = "controller?command=home";
				}
			} catch (ServiceException e) {
				// TODO Дописать, скорее всего, такого вида ошибки - error 500
				// =>
				// перенаправление!!!!
				request.setAttribute(ATTRIBUTE_ERROR_LOGIN_MESSAGE, e.getMessage());

			} catch (ServiceValidationException e) {
				// TODO logging и дописать!!!
				request.setAttribute(ATTRIBUTE_ERROR_MESSAGE_SET, e.getErrorSet());
				page = ConfigurationManager.getProperty(SIGN_UP_PAGE_URL);
			}

			Map<String, String[]> map = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String[] arr = entry.getValue();
				System.out.println(entry.getKey() + "= " + Arrays.deepToString(entry.getValue()));
			}
		}

		return page;
	}

}
