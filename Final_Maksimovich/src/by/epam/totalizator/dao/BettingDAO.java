package by.epam.totalizator.dao;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.dao.exception.DAOException;

public interface BettingDAO {

	public void makeBetting(Betting betting) throws DAOException;;

	public Betting getBettingInfo(int id) throws DAOException;;

	public void cancelBetting(int id) throws DAOException;
}
