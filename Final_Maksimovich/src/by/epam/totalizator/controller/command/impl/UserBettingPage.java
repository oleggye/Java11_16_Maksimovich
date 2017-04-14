package by.epam.totalizator.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageKeyStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.controller.util.SupportClass;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class UserBettingPage implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(UserBettingPage.class.getName());
	/**
	 * count of records on one page
	 */
	private static final int RECORD_QUANTITY_PER_PAGE = 5;
	/**
	 * command name for creating pagination (this command name be placed in
	 * &lt;a&gt; link tag)
	 */
	private static final String USER_BETTING_COMMAND = "user-betting";
	/**
	 * url pattern for this command to put in user's session
	 */
	private static final String COMMAND_URL_PATTERN = "controller?command=user-betting&pageNumber=%s";

	/**
	 * Performs a service level call and takes {@link java.util.List} of
	 * {@link Betting} and obtain betting count.
	 * 
	 * Sets these objects and {@link #USER_BETTING_COMMAND} to the request and
	 * formed {@link #COMMAND_URL_PATTERN} to the user's session as attributes.
	 * 
	 * If success sets attributes and forwards request and response to
	 * userManagement.jsp
	 * 
	 * If catch {@link ServiceException} than redirects to internalError.jsp
	 * 
	 * If catch {@link ServiceValidationException} than sets error message text
	 * as the request attribute and forwards request and response to
	 * previousPage from the user's session
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
		String page = null;

		Locale locale = getLocale(request);
		int pageNumber = getPageNumber(request);

		int IdUser = (Integer) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_ID_USER);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {

			List<Betting> bettingList = factory.getBettingService().obtainUserBettingList(IdUser, pageNumber,
					RECORD_QUANTITY_PER_PAGE, locale);

			int availableCount = factory.getBettingService().obtainUserBettingListCount(IdUser);
			int pageCount = calculatePageCount(availableCount);

			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_BETTING_LIST, bettingList);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_COMMAND, USER_BETTING_COMMAND);

			String pageUrl = String.format(COMMAND_URL_PATTERN, pageNumber);
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, pageUrl);

			page = ConfigurationManager.getProperty(PageKeyStore.USER_BETTING_PAGE_KEY);
			request.getRequestDispatcher(page).forward(request, response);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e);
			page = ConfigurationManager.getProperty(PageKeyStore.INTERNAL_ERROR_PAGE_KEY);
			response.sendRedirect(page);

		} catch (ServiceValidationException e) {
			LOGGER.log(Level.WARN, e);
			page = (String) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	/**
	 * Method gets {@link java.util.Locale} from the user's session
	 */
	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	/**
	 * Method gets page number string from the user's request and delegate
	 * safety parsing of it to
	 * {@link by.epam.totalizator.controller.util.SupportClass#parsePageNumber}
	 */
	private int getPageNumber(HttpServletRequest request) {

		String pageNumberParam = request.getParameter(ParamNameStore.PARAM_NAME_PAGE_NUMBER);
		int pageNumber = SupportClass.parsePageNumber(pageNumberParam);

		return pageNumber;
	}

	/**
	 * Method delegates calculating page count to it to
	 * {@link by.epam.totalizator.controller.util.SupportClass#calculatePageCount}
	 */
	private int calculatePageCount(int availableCount) {
		return SupportClass.calculatePageCount(availableCount, RECORD_QUANTITY_PER_PAGE);
	}
}
