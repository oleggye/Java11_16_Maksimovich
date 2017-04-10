package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.resource.ConfigurationManager;

public class SignInPage implements ICommand {

	private static final String COMMAND_URL = "controller?command=signin-page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = ConfigurationManager.getProperty(PageNameStore.SIGNIN_PAGE_URL);

		request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, COMMAND_URL);

		request.getRequestDispatcher(page).forward(request, response);
	}
}
