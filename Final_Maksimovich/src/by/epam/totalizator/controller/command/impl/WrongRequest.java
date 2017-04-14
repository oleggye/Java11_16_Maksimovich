package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.PageKeyStore;
import by.epam.totalizator.resource.ConfigurationManager;

/**
 * Instance of {@link ICommand}
 */
public class WrongRequest implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(WrongRequest.class.getName());

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page = ConfigurationManager.getProperty(PageKeyStore.ERROR_PAGE_KEY);

		LOGGER.log(Level.WARN, "WrongRequest");
		request.getRequestDispatcher(page).forward(request, response);
	}
}
