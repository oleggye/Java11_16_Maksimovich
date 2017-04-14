package by.epam.totalizator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface BettingService {

	public void makeBetting(Betting betting, BigDecimal userBalance) throws ServiceException, ServiceValidationException;

	public List<Betting> obtainUserBettingList(int idUser, int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException;

	public int obtainUserBettingListCount(int idUser) throws ServiceException, ServiceValidationException;
}
