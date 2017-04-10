package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SignIn implements ICommand {

	private static final String COMMAND_PATTERN = "controller?command=%s";
	private static final String ATTRIBUTE_NAME_DEFAULT_COMMAND_VALUE = "home";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = null;

		String login = request.getParameter(ParamNameStore.PARAM_NAME_LOGIN);
		String password = request.getParameter(ParamNameStore.PARAM_NAME_PASSWORD);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			User user = factory.getClientService().signIn(login, password);

			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER, user.getId());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOGIN, user.getEmail());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_CURRENCY, user.getCurrency());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE, user.getBalance());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER_TYPE, user.getUserType());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL, user.getLocale());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BANNED, user.isBanned());

			Object previousCommand = request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_COMMAND);
			if (previousCommand != null) {
				page = String.format(COMMAND_PATTERN, previousCommand);
			} else {
				page = String.format(COMMAND_PATTERN, ATTRIBUTE_NAME_DEFAULT_COMMAND_VALUE);
			}
			response.sendRedirect(page);

		} catch (ServiceException e) {
			// TODO: logging
			page = ConfigurationManager.getProperty(PageNameStore.INTERNAL_ERROR_PAGE_URL);
			request.getRequestDispatcher(page).forward(request, response);

		} catch (ServiceValidationException e) {
			// TODO: logging
			page = ConfigurationManager.getProperty(PageNameStore.SIGNIN_PAGE_URL);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(page).forward(request, response);
		}
	}
}
