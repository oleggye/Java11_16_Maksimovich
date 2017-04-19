package by.epam.totalizator.controller.command.impl.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import by.epam.totalizator.controller.util.SupportClass;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class MakeBetting implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(MakeBetting.class.getName());

	private static final String RESPONSE_CONTENT_TYPE = "text/plain";

	private static final String SUCCESS_MESSAGE_KEY = "local.ajax.add_betting.success";
	private static final String WRONG_PARAM_MESSAGE_KEY = "local.ajax.wrong_param";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE_KEY = "local.internal-server-error";

	/**
	 * Forms {@link Betting} bean for new betting and performs a service level
	 * call.
	 * 
	 * If success receives a message by key {@value #SUCCESS_MESSAGE_KEY} and
	 * sends it back.
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

		Locale locale = getLocale(request);

		try {
			Betting betting = parseRequestParam(request);
			BigDecimal userBalance = getUserBalance(request);

			ServiceFactory factory = ServiceFactory.getInstance();

			factory.getBettingService().makeBetting(betting, userBalance, locale);

			BigDecimal newUserBalance = userBalance.subtract(betting.getBetSize());
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE, newUserBalance);
			statusCode = HttpServletResponse.SC_OK;
			message = LocalizationBundle.getProperty(locale, SUCCESS_MESSAGE_KEY);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = LocalizationBundle.getProperty(locale, INTERNAL_SERVER_ERROR_MESSAGE_KEY);

		} catch (ServiceValidationException e) {
			LOGGER.log(Level.WARN, e);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message = LocalizationBundle.getProperty(locale, WRONG_PARAM_MESSAGE_KEY) + e.getMessage();
		}
		sendMessage(response, statusCode, message);
	}

	/**
	 * Method forms {@link java.util.Locale} from the user's session
	 */
	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
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

	/**
	 * Method gets {@link Betting} bean from request
	 */
	private Betting parseRequestParam(HttpServletRequest request) {

		int idUser = (Integer) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER);
		User user = new UserBuilder().buildId(idUser).build();

		int idCompetition = Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_ID_COMPETITION));
		Competition competition = new CompetitionBuilder().buildId(idCompetition).build();

		String betTypeParam = request.getParameter(ParamNameStore.PARAM_NAME_BETTING_TYPE);
		EventType betType = EventType.getTypeByShortName(betTypeParam);

		BigDecimal betSize = getBet(request, ParamNameStore.PARAM_NAME_BETTING_SIZE);

		BigDecimal betRate = getBet(request, ParamNameStore.PARAM_NAME_BETTING_RATE);

		Betting betting = new BettingBuilder().buildUser(user).buildCompetition(competition).buildBetType(betType)
				.buildBetSize(betSize).buildBetRate(betRate).build();

		return betting;
	}

	/**
	 * Method gets balance value from the user's session
	 * 
	 */
	private BigDecimal getUserBalance(HttpServletRequest request) {
		BigDecimal userBalance = (BigDecimal) request.getSession()
				.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_BALANCE);
		return userBalance;
	}

	/**
	 * Method gets bet string from the request and delegate safety parsing of it
	 * to
	 * {@link by.epam.totalizator.controller.util.SupportClass#parseBigDecimal}
	 */
	private BigDecimal getBet(HttpServletRequest request, String key) {
		String betParam = request.getParameter(key);
		return SupportClass.parseBigDecimal(betParam);
	}
}
