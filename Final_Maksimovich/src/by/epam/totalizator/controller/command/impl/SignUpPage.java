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

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SignUpPage implements ICommand {

	private static final Logger logger = LogManager.getLogger(SignUpPage.class.getName());

	private static final String COMMAND_URL = "controller?command=signup-page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Locale locale = getLocale(request);

		String page = ConfigurationManager.getProperty(PageNameStore.SIGN_UP_PAGE_URL);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			List<Country> countryList = factory.getCountryService().obtainCountryList(locale);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_COUNTRY_LIST, countryList);

			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, COMMAND_URL);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			page = ConfigurationManager.getProperty(PageNameStore.INTERNAL_ERROR_PAGE_URL);
			response.sendRedirect(page);

		} catch (ServiceValidationException e) {
			logger.log(Level.ERROR, e);
			page = (String) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			response.sendRedirect(page);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}
}
