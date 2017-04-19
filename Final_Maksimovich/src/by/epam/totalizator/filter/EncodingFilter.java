package by.epam.totalizator.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

	private String encoding;

	/**
	 * Method is used to set encoding for the request and response objects
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String requestEncoding = request.getCharacterEncoding();

		if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
			request.setCharacterEncoding(encoding);
		}
		response.setCharacterEncoding(encoding);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		encoding = null;
	}
}
