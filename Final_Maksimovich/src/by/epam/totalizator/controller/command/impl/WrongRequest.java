package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.resource.ConfigurationManager;

public class WrongRequest implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page = ConfigurationManager.getProperty(PageNameStore.ERROR_PAGE_URL);
		request.getRequestDispatcher(page).forward(request, response);
	}
}
