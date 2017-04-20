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
public class ChangeLocale implements ICommand {

	/**
	 * url that will be used if there is not #ATTRIBUTE_PREVIOUS_PAGE_ULR
	 * attribute in the user's session
	 */
	private static final String ATTRIBUTE_NAME_DEFAULT_URL = "controller?command=home";

	/**
	 * This command not exactly change language, because it happens in
	 * {@link by.epam.totalizator.filter.LocalizationFIlter}
	 * 
	 * The main purpose of the command is to redirect client to a previous page
	 * which value is taken from the user's session attribute or from
	 * {@link #ATTRIBUTE_NAME_DEFAULT_URL}
	 * 
	 * @param request
	 *            contains a user request object from
	 *            {@link by.epam.totalizator.controller.Controller#processRequest}
	 * 
	 * @param response
	 *            contains the response object which will be send to the user
	 *
	 * @throws throws
	 *             IOException
	 * @throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page = null;

		Object previousCommand = request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);

		if (previousCommand != null) {
			page = (String) previousCommand;
		} else {
			page = ATTRIBUTE_NAME_DEFAULT_URL;
		}
		response.sendRedirect(page);
	}
}
