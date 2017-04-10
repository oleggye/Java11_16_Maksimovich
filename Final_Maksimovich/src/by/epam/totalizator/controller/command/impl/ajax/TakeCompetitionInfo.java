package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class TakeCompetitionInfo implements ICommand {

	private static final Logger logger = LogManager.getLogger(TakeCompetitionInfo.class.getName());

	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	private static final String RESPONSE_JSON_CONTENT_TYPE = "application/json";
	private static final String RESPONSE_TEXT_CONTENT_TYPE = "text/plain";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String message = null;
		int statusCode;

		Locale locale = getLocale(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			int idCompetition = getIdCompetition(request);

			Competition competition = factory.getCompetitionService().obtainCompetition(idCompetition, locale);

			message = new Gson().toJson(competition);
			statusCode = HttpServletResponse.SC_OK;
			response.setContentType(RESPONSE_JSON_CONTENT_TYPE);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			response.setContentType(RESPONSE_TEXT_CONTENT_TYPE);
			message = LocalizationBundle.getInstance().getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | NumberFormatException e) {
			logger.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			response.setContentType(RESPONSE_TEXT_CONTENT_TYPE);
			message = LocalizationBundle.getInstance().getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();
		}
		sendMessage(response, statusCode, message);
	}

	private int getIdCompetition(HttpServletRequest request) {
		return Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_COMPETITION));
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	private void sendMessage(HttpServletResponse response, int statusCode, String message) throws IOException {
		response.setStatus(statusCode);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}
}
