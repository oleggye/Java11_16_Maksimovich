package by.epam.totalizator.service.impl;

import by.epam.totalizator.service.BettingService;
import by.epam.totalizator.service.ValidationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class BettingServiceImpl implements BettingService {

	private static final BigDecimal DEFAULT_BETTING_GAIN = new BigDecimal(0.00);

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
			throw new ServiceException("Competition service problem", e);
		}
	}

	@Override
	public Betting getBettingInfo(int idBetting) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBetting(int idBetting) throws ServiceException, ServiceValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Betting> obtainUserBettingList(int idUser, int pageNumber, int recordQuantityPerPage, Locale locale)
			throws ServiceException, ServiceValidationException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		serviceFactory.getValidationService().validatePageNumber(pageNumber);

		DAOFactory factory = DAOFactory.getInstance();
		List<Betting> bettingList;
		int fromRecord = (pageNumber - 1) * (recordQuantityPerPage);

		try {
			bettingList = factory.getBettingDAO().obtainUserBettingList(idUser, fromRecord, recordQuantityPerPage,
					locale);

		} catch (DAOException e) {
			throw new ServiceException("Client service problem", e);
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
			throw new ServiceException("Competition service problem", e);
		}
		return count;
	}

	@Override
	public void enrollAWin(int idCompetition, EventType result) throws ServiceException, ServiceValidationException {

		ValidationService validationService = ServiceFactory.getInstance().getValidationService();
		validationService.validateIdCompetition(idCompetition);
		validationService.validateEventType(result);

		DAOFactory factory = DAOFactory.getInstance();

		try {
			List<Betting> bettingList = factory.getBettingDAO().obtainBettingListForComeptition(idCompetition);

			ListIterator<Betting> listIterator = bettingList.listIterator();

			Betting betting;
			while (listIterator.hasNext()) {
				betting = listIterator.next();

				int idBetting = betting.getId();
				if (betting.getBetType().equals(result)) {

					int idUser = betting.getUser().getId();
					BigDecimal betSize = betting.getBetSize();
					BigDecimal betRate = betting.getBetRate();
					BigDecimal amount = betSize.multiply(betRate);

					// задаем положительный выйгрыш для ставки
					factory.getBettingDAO().defineBettingGain(idBetting, amount);
					// обновляем баланс пользователя
					factory.getUserDAO().changeUserBalance(idUser, amount);

				} else {
					// задаем "нулевой" выигрыш для ставки
					factory.getBettingDAO().defineBettingGain(idBetting, DEFAULT_BETTING_GAIN);
				}
			}

		} catch (DAOException e) {
			throw new ServiceException("Competition service problem", e);
		}
	}

}
