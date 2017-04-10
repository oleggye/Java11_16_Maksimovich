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
import by.epam.totalizator.controller.util.PageNameStore;
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

	private Set<CommandName> commandSetForUnauthorizedUser = new HashSet<>();

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

		boolean isPermited;
		UserType userType = (UserType) httpRequest.getSession()
				.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_USER_TYPE);
		String commandParam = httpRequest.getParameter(PARAM_NAME_COMMAND);

		if (userType == null && commandParam != null) {

			try {
				CommandName commandName = CommandName.valueOf(commandParam.toUpperCase().replace(OLD_CHAR, NEW_CHAR));
				isPermited = commandSetForUnauthorizedUser.contains(commandName);

				if (!isPermited) {
					String page = ConfigurationManager.getProperty(PageNameStore.ERROR_PAGE_URL);
					request.getRequestDispatcher(page).forward(request, response);
				} else {
					chain.doFilter(request, response);
				}

			} catch (IllegalArgumentException e) {
				logger.log(Level.WARN, e);
				String page = ConfigurationManager.getProperty(PageNameStore.ERROR_PAGE_URL);
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
		commandSetForUnauthorizedUser.add(CommandName.HOME);
		commandSetForUnauthorizedUser.add(CommandName.RESULT);
		commandSetForUnauthorizedUser.add(CommandName.TOURNAMENT_PAGE);
		commandSetForUnauthorizedUser.add(CommandName.SIGNIN_PAGE);
		commandSetForUnauthorizedUser.add(CommandName.SIGNIN);
		commandSetForUnauthorizedUser.add(CommandName.SIGNUP);
		commandSetForUnauthorizedUser.add(CommandName.SIGNUP_PAGE);
		commandSetForUnauthorizedUser.add(CommandName.CHANGE_LOCALE);
	}

}
