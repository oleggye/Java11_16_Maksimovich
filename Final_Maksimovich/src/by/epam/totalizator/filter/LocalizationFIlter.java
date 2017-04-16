package by.epam.totalizator.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;

public class LocalizationFIlter implements Filter {

	private Locale defaultLocale;

	private static final String PARAM_NAME_DEFAULT_LOCALE = "defaultLocale";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession(true);
		Object userLocaleParam = session.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);

		if (userLocaleParam == null) {
			session.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL, defaultLocale);
		}

		String requestLocalParam = request.getParameter(ParamNameStore.PARAM_NAME_LOCAL);
		if (requestLocalParam != null) {
			Locale locale = new Locale(requestLocalParam);
			session.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL, locale);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		defaultLocale = new Locale(fConfig.getInitParameter(PARAM_NAME_DEFAULT_LOCALE));
	}

	public void destroy() {
		defaultLocale = null;
	}

}
