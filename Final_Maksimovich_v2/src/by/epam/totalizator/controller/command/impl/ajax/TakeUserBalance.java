package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * Instance of {@link ICommand}
 */
public class TakeUserBalance implements ICommand {

	private static final String RESPONSE_CONTENT_TYPE = "application/json";
	/**
	 * cout of object which will be serialized
	 */
	private static final int RESPONSE_ELEMENT_COUNT = 2;

	/**
	 * Takes balance and currency from the user's session attributes
	 * 
	 * Serializes this objects and sends it back.
	 * 
	 * If catch {@link ServiceException} than receives a message by key
	 * {@value #INTERNAL_SERVER_ERROR_MESSAGE_KEY} and sends it back.
	 * 
	 * If catch {@link ServiceValidationException} than receives a message by
	 * key {@value #WRONG_PARAM_MESSAGE_KEY} and sends it back.
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

		response.setContentType(RESPONSE_CONTENT_TYPE);

		String message = null;
		int statusCode;

		Object balanceParam = request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE);
		Object currencyParam = request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_CURRENCY);

		List<Object> messageList = new ArrayList<>(RESPONSE_ELEMENT_COUNT);
		messageList.add(balanceParam);
		messageList.add(currencyParam);

		message = new Gson().toJson(messageList);

		statusCode = HttpServletResponse.SC_OK;

		sendMessage(response, statusCode, message);
	}

	/**
	 * The method generates a response to the user
	 */
	private void sendMessage(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}
}