package by.epam.totalizator.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for Command pattern realization
 */
public interface ICommand {

	/**
	 * 
	 * @param HttpServletRequest
	 *            request from application's client
	 * @param HttpServletResponse
	 *            response which will be send to the client
	 * @throws IOException
	 * @throws ServletException
	 */

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
