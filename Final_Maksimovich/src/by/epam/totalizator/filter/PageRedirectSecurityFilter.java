package by.epam.totalizator.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(urlPatterns = "/jsp/*")
public class PageRedirectSecurityFilter implements Filter {

	private static final String PARAM_NAME_COMMAND = "command";

	/**
	 * Method is used to prevent direct access to jsp pages and for request
	 * without the command attribute
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String command = httpRequest.getParameter(PARAM_NAME_COMMAND);

		if (command != null) {

			httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller?command=" + command);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller?command=home");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}
}
