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

public class TakeUserBalance implements ICommand {

	private static final String RESPONSE_CONTENT_TYPE = "application/json";
	private static final int RESPONSE_ELEMENT_COUNT = 2;

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

	private void sendMessage(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}
}