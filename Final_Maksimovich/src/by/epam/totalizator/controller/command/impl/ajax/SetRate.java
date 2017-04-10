package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.build.CompetitionBuilder;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SetRate implements ICommand {

	private static final Logger logger = LogManager.getLogger(SetRate.class.getName());

	private static final String RESPONSE_CONTENT_TYPE = "text/plain";

	private static final String SUCCESS_MESSAGE_KEY = "local.ajax.update_competition.success";
	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "Internal Server Error!";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType(RESPONSE_CONTENT_TYPE);

		String message = null;
		int statusCode;

		Locale locale = getLocale(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {
			Competition competition = parseRequest(request);

			factory.getCompetitionService().updateComeptititonRate(competition);

			statusCode = HttpServletResponse.SC_OK;
			message = LocalizationBundle.getInstance().getProperty(locale, SUCCESS_MESSAGE_KEY);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = LocalizationBundle.getInstance().getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | NumberFormatException | ParseException e) {
			logger.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message = LocalizationBundle.getInstance().getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();

		}
		sendMessage(response, statusCode, message);
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	private Competition parseRequest(HttpServletRequest request) throws ParseException {
		int idCompetition = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_COMPETITION));
		BigDecimal homeRate = new BigDecimal(request.getParameter(ParamNameStore.PARAM_NAME_HOME_RATE));
		BigDecimal drawRate = new BigDecimal(request.getParameter(ParamNameStore.PARAM_NAME_DRAW_RATE));
		BigDecimal awayRate = new BigDecimal(request.getParameter(ParamNameStore.PARAM_NAME_AWAY_RATE));

		Competition competition = new CompetitionBuilder().buildId(idCompetition).builderWinHomeRate(homeRate)
				.buildDrawRate(drawRate).buildWinAwayRate(awayRate).build();

		return competition;
	}

	private void sendMessage(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}
}
