package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;

/**
 * Instance of {@link ICommand}
 */
public class SignOut implements ICommand {

	/**
	 * url to home.jsp
	 */
	private static final String HOME_PAGE_URL = "controller?command=home";

	/**
	 * Removes user's session attributes and sends redirect to
	 * {@value #HOME_PAGE_URL}
	 * 
	 * @param request
	 *            contains a user request object from
	 *            {@link by.epam.totalizator.controller.Controller#processRequest}
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER);
		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOGIN);
		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_CURRENCY);
		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE);
		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER_TYPE);
		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_NAME_BANNED);

		String page = HOME_PAGE_URL;
		response.sendRedirect(page);
	}
}
