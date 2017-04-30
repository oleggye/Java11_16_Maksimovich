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

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class LocalizationFIlter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession(true);
		Object userSessionLocaleParam = 
				session.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);

		if (userSessionLocaleParam == null) {
			Locale userLocale = getSuitableLocalization(request.getLocale());
			session.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL, userLocale);
		}

		String requestLocaleParam = request.getParameter(ParamNameStore.PARAM_NAME_LOCAL);
		if (requestLocaleParam != null) {
			Locale locale = new Locale(requestLocaleParam);
			session.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL, locale);
		}

		chain.doFilter(request, response);
	}

	/**
	 * Method checks whether the localization is in the set of allowable values
	 * 
	 * if isn't than returns #Locale.US value
	 * else returns the passed localization
	 * 
	 * @param locale
	 *            testable localization
	 * @return suitable locale for the app
	 */

	private Locale getSuitableLocalization(Locale locale) {

		String localeLanguage = locale.getLanguage();
		if (locale == null 
				|| !localeLanguage.equals(Locale.US.getLanguage()) 
				|| !localeLanguage.equalsIgnoreCase("ru")) {
			locale = Locale.US;
		}
		return locale;
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