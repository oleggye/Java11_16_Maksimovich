package by.epam.totalizator.service;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface BettingService {

	public void makeBetting(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException;

	public Betting getBettingInfo(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;

	public void cancelBetting(SessionRequestContent requestContent) throws ServiceException, ServiceValidationException;

	public void obtainUserBettingList(SessionRequestContent requestContent)
			throws ServiceException, ServiceValidationException;
}
