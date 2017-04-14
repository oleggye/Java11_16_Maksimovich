package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageKeyStore;
import by.epam.totalizator.resource.ConfigurationManager;

/**
 * Instance of {@link ICommand}
 */
public class SignInPage implements ICommand {

	/**
	 * command url for forwarding request and response to singIn.jsp
	 */
	private static final String COMMAND_URL = "controller?command=signin-page";

	/**
	 * Sets in the user's session previous page attribute {@value #COMMAND_URL}
	 * and forwards request to signIn.jsp
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, COMMAND_URL);

		String page = ConfigurationManager.getProperty(PageKeyStore.SIGNIN_PAGE_KEY);

		request.getRequestDispatcher(page).forward(request, response);
	}
}
