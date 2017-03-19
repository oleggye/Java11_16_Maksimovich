package by.epam.totalizator.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalizationFIlter implements Filter {

	String defaultLocale;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession(true);
		String userLocale = (String) session.getAttribute("local");

		if (userLocale == null) {
			session.setAttribute("local", defaultLocale);
		}

		String requestLocalParam = request.getParameter("local");
		if (requestLocalParam != null) {
			session.setAttribute("local", requestLocalParam);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		defaultLocale = fConfig.getInitParameter("defaultLocale");
	}

	public void destroy() {
		defaultLocale = null;
	}

}
