package by.epam.totalizator.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.UserType;
import by.epam.totalizator.controller.command.CommandName;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageKeyStore;
import by.epam.totalizator.resource.ConfigurationManager;

/**
 * Servlet Filter implementation class CommandSecurityFilter
 */
@WebFilter(urlPatterns = "/controller")
public class CommandSecurityFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(CommandSecurityFilter.class.getName());

	private static final String PARAM_NAME_COMMAND = "command";
	private static final char OLD_CHAR = '-';
	private static final char NEW_CHAR = '_';

	private Set<CommandName> commandSetForUnauthorizedOrBannedUser = new HashSet<>();

	/**
	 * Default constructor.
	 */
	public CommandSecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		UserType userType = (UserType) httpRequest.getSession()
				.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER_TYPE);
		Boolean isBanned = (Boolean) httpRequest.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_BANNED);

		String commandParam = httpRequest.getParameter(PARAM_NAME_COMMAND);

		if ((userType == null || isBanned) && commandParam != null) {

			try {
				CommandName commandName = CommandName.valueOf(commandParam.toUpperCase().replace(OLD_CHAR, NEW_CHAR));
				boolean isPermited = commandSetForUnauthorizedOrBannedUser.contains(commandName);

				if (!isPermited) {
					String page = ConfigurationManager.getProperty(PageKeyStore.ERROR_PAGE_KEY);
					request.getRequestDispatcher(page).forward(request, response);
				} else {
					chain.doFilter(request, response);
				}

			} catch (IllegalArgumentException e) {
				logger.log(Level.WARN, e);
				String page = ConfigurationManager.getProperty(PageKeyStore.ERROR_PAGE_KEY);
				request.getRequestDispatcher(page).forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		commandSetForUnauthorizedOrBannedUser.add(CommandName.HOME);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.RESULT);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.TOURNAMENT_PAGE);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.SIGNIN_PAGE);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.SIGNIN);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.SIGNUP);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.SIGN_OUT);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.SIGNUP_PAGE);
		commandSetForUnauthorizedOrBannedUser.add(CommandName.CHANGE_LOCALE);
	}

}
