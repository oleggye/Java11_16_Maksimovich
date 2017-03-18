package by.epam.totalizator.service.impl;

import by.epam.totalizator.service.BettingService;

import java.util.List;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;
import by.epam.totalizator.service.util.SupportClass;

public class BettingServiceImpl implements BettingService {

	private static final String PARAM_NAME_USERID = "userId";
	private static final String PARAM_NAME_PAGE_NUMBER = "pageNumber";
	private static final int RECORDS_PER_PAGE = 6;
	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final String ATTRIBUTE_NAME_BETTING_LIST = "bettingList";
	private static final String ATTRIBUTE_NAME_PAGE_COUNT = "count";
	private static final String ATTRIBUTE_NAME_PAGE_NUMBER = "pageNumber";

	@Override
	public void makeBetting(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public Betting getBettingInfo(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBetting(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void obtainUserBettingList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException {

		int userId = (Integer) requestContent.getSessionParam(PARAM_NAME_USERID);

		String pageNumberParam = requestContent.getRequestParam(PARAM_NAME_PAGE_NUMBER);
		int pageNumber = 0;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			serviceFactory.getValidationService().validatePageNumber(pageNumberParam);
			pageNumber = Integer.parseInt(pageNumberParam);
		}

		DAOFactory factory = DAOFactory.getInstance();
		List<Betting> bettingList;
		int fromRecord = (pageNumber - 1) * (RECORDS_PER_PAGE);

		try {
			bettingList = factory.getBettingDAO().obtainUserBettingList(userId, fromRecord, RECORDS_PER_PAGE);

			int availableCount = obtainUserBettingsCount(userId);
			int pageCount = SupportClass.calcPageCount(availableCount, RECORDS_PER_PAGE);
			System.out.println(pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_BETTING_LIST, bettingList);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_COUNT, pageCount);
			requestContent.putRequestAttribute(ATTRIBUTE_NAME_PAGE_NUMBER, pageNumber);

		} catch (DAOException e) {
			throw new ServiceException("Client service problem", e);
		}

	}

	private int obtainUserBettingsCount(int userId) throws ServiceException, ServiceValidationException {

		int count;

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getBettingDAO().obtainUserBettingCount(userId);
		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

}
