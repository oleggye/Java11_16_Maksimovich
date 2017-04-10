package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.build.BettingBuilder;
import by.epam.totalizator.bean.build.CompetitionBuilder;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class MakeBetting implements ICommand {

	private static final Logger logger = LogManager.getLogger(MakeBetting.class.getName());

	private static final String RESPONSE_CONTENT_TYPE = "text/plain";

	private static final String SUCCESS_MESSAGE_KEY = "local.ajax.add_betting.success";
	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType(RESPONSE_CONTENT_TYPE);

		String message = null;
		int statusCode;

		Locale locale = getLocale(request);

		try {
			Betting betting = parseRequestParam(request);
			BigDecimal userBalance = getUserBalance(request);

			ServiceFactory factory = ServiceFactory.getInstance();

			factory.getBettingService().makeBetting(betting, userBalance);

			BigDecimal newUserBalance = userBalance.subtract(betting.getBetSize());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE, newUserBalance);
			statusCode = HttpServletResponse.SC_OK;
			message = LocalizationBundle.getInstance().getProperty(locale, SUCCESS_MESSAGE_KEY);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = LocalizationBundle.getInstance().getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException | NumberFormatException e) {
			logger.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message = LocalizationBundle.getInstance().getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();
		}
		sendMessage(response, statusCode, message);
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	private void sendMessage(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}

	private Betting parseRequestParam(HttpServletRequest request) {

		int idUser = (Integer) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER);
		User user = new UserBuilder().buildId(idUser).build();

		int idCompetition = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_COMPETITION));
		Competition competition = new CompetitionBuilder().buildId(idCompetition).build();

		String betTypeParam = request.getParameter(ParamNameStore.PARAM_NAME_BETTING_TYPE);
		EventType betType = EventType.getTypeByShortName(betTypeParam);

		String betSizeParam = request.getParameter(ParamNameStore.PARAM_NAME_BETTING_SIZE);
		BigDecimal betSize = new BigDecimal(betSizeParam);

		String betRateParam = request.getParameter(ParamNameStore.PARAM_NAME_BETTING_RATE);
		BigDecimal betRate = new BigDecimal(betRateParam);

		Betting betting = new BettingBuilder().buildUser(user).buildCompetition(competition).buildBetType(betType)
				.buildBetSize(betSize).buildBetRate(betRate).build();

		return betting;
	}

	private BigDecimal getUserBalance(HttpServletRequest request) {
		BigDecimal userBalance = (BigDecimal) request.getSession()
				.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE);
		return userBalance;
	}
}
