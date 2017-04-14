package by.epam.totalizator.service.impl;

import by.epam.totalizator.service.BettingService;
import by.epam.totalizator.service.ValidationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class BettingServiceImpl implements BettingService {
	
	private static final String BETTING_SERVICE_EXCEPTION_MESSAGE ="BettingService problem";

	@Override
	public void makeBetting(Betting betting, BigDecimal userBalance)
			throws ServiceException, ServiceValidationException {

		ValidationService validationService = ServiceFactory.getInstance().getValidationService();
		validationService.validateBetting(betting);

		// TODO: can't find better place to check this condition
		if (betting.getBetSize().compareTo(userBalance) != -1) {
			throw new ServiceValidationException("Wrong betSize:" + betting.getBetSize());
		}
		int idUser = betting.getUser().getId();

		DAOFactory factory = DAOFactory.getInstance();
		try {
			factory.getBettingDAO().makeBetting(betting);
			factory.getUserDAO().changeUserBalance(idUser, betting.getBetSize().negate());
		} catch (DAOException e) {
			throw new ServiceException(BETTING_SERVICE_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public List<Betting> obtainUserBettingList(int idUser, int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		ValidationService validation = ServiceFactory.getInstance().getValidationService();
		validation.validatePageNumber(pageNumber);
		validation.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		List<Betting> bettingList;
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			bettingList = factory.getBettingDAO().obtainUserBettingList(idUser, fromRecord, recordQuantityPerPage,
					locale);

		} catch (DAOException e) {
			throw new ServiceException(BETTING_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return bettingList;
	}

	@Override
	public int obtainUserBettingListCount(int idUser) throws ServiceException, ServiceValidationException {
		int count;

		ValidationService validationService = ServiceFactory.getInstance().getValidationService();
		validationService.validateIdUser(idUser);

		DAOFactory factory = DAOFactory.getInstance();
		try {
			count = factory.getBettingDAO().obtainUserBettingCount(idUser);
		} catch (DAOException e) {
			throw new ServiceException(BETTING_SERVICE_EXCEPTION_MESSAGE, e);
		}
		return count;
	}

}
