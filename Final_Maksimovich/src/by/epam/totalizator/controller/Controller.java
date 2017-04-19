package by.epam.totalizator.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.ParamNameStore;

/**
 * Front controller of the application
 * 
 * Handles HTTP requests and HTTP response to certain instance of
 * {@link ICommand}
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * Called by the server to handle client GET request type
	 * 
	 * Delegates request processing to
	 * {@link #processRequest(HttpServletRequest, HttpServletResponse)}
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Called by the server to handle client POST request type
	 * 
	 * Delegates request processing to
	 * {@link #processRequest(HttpServletRequest, HttpServletResponse)}
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Method, which processing both POST and GET request from a server client.
	 * and return the response.
	 * 
	 * Defining type of command, getting certain instance of {@link ICommand}
	 * and execute it.
	 * 
	 * @param request
	 *            contains the request object of a server client
	 * @param response
	 *            contains the response object which will be send to the client
	 * @throws ServletException
	 *             if an input or output error is detected when the servlet
	 *             handles the request
	 * @throws IOException
	 *             if the request could not be handled
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommandProvider provider = CommandProvider.getInstance();
		ICommand command = provider.getCommand(request.getParameter(ParamNameStore.PARAM_NAME_COMMAND));

		command.execute(request, response);
	}
}
