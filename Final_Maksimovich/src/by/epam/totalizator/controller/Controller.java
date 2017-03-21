package by.epam.totalizator.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.Command;

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String PARAM_NAME_COMMAND = "command";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;

		CommandProvider provider = CommandProvider.getInstance();
		Command command = provider.getCommand(request.getParameter(PARAM_NAME_COMMAND));
		page = command.execute(request);

		if (page != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			// TODO: ЧТО ДЕЛАТЬ С EXCEPTIONS????
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
			// Редерект на error-page, подумать
		}
	}

}
