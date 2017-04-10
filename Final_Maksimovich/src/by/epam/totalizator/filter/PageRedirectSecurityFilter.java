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
	 * Default constructor.
	 */
	public PageRedirectSecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// переход на заданную страницу

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
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
