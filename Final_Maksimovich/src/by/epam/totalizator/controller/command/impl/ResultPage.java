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

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.controller.util.SupportClass;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class ResultPage implements ICommand {

	private static final Logger logger = LogManager.getLogger(ResultPage.class.getName());

	private static final int RECORD_QUANTITY_PER_PAGE = 5;
	private static final String RESULT_COMMAND = "result";
	private static final String COMMAND_URL_PATTERN = "controller?command=result&pageNumber=%s";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = null;

		request.getSession().removeAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE);

		Locale locale = getLocale(request);
		int pageNumber = getPageNumber(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		try {

			List<Competition> competitionList = factory.getCompetitionService().obtainCompetitionResultList(pageNumber,
					RECORD_QUANTITY_PER_PAGE, locale);

			int competitionRecordQuantity = factory.getCompetitionService().obtainCompetitionResultListCount();
			int pageCount = calculatePageCount(competitionRecordQuantity);

			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_COMPETITION_LIST, competitionList);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);
			request.setAttribute(AttributeNameStore.ATTRIBUTE_NAME_COMMAND, RESULT_COMMAND);

			String pageUrl = String.format(COMMAND_URL_PATTERN, pageNumber);
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR, pageUrl);

			page = ConfigurationManager.getProperty(PageNameStore.RESULT_PAGE_URL);
			request.getRequestDispatcher(page).forward(request, response);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			page = ConfigurationManager.getProperty(PageNameStore.INTERNAL_ERROR_PAGE_URL);
			response.sendRedirect(page);

		} catch (NumberFormatException | ServiceValidationException e) {
			logger.log(Level.WARN, e);
			page = (String) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);
			request.getSession().setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
			response.sendRedirect(page);
		}
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	private int getPageNumber(HttpServletRequest request) {

		String pageNumberParam = request.getParameter(ParamNameStore.PARAM_NAME_PAGE_NUMBER);
		int pageNumber = SupportClass.parsePageNumber(pageNumberParam);

		return pageNumber;
	}

	private int calculatePageCount(int availableCount) {
		return SupportClass.calculatePageCount(availableCount, RECORD_QUANTITY_PER_PAGE);
	}
}