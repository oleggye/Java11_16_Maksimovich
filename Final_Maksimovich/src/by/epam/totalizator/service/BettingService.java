package by.epam.totalizator.service;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.service.exception.ServiceException;

public interface BettingService {

	public void makeBetting(Betting betting) throws ServiceException;;

	public Betting getBettingInfo(int id) throws ServiceException;;

	public void cancelBetting(int id) throws ServiceException;

}
