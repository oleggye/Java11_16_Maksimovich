package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.controller.command.ICommand;

/**
 * Instance of {@link ICommand}
 */
public class WrongRequest implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(WrongRequest.class.getName());

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LOGGER.log(Level.WARN, "WrongRequest");
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}