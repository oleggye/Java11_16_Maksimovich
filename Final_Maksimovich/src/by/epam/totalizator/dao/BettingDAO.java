package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface BettingDAO {

	public void makeBetting(Betting betting) throws DAOException;;

	public Betting getBettingInfo(int id) throws DAOException;;

	public void cancelBetting(int id) throws DAOException;

	public List<Betting> obtainUserBettingList(int userId, int fromRecord, int recordCount)
			throws DAOException;

	public int obtainUserBettingCount(int userId) throws DAOException;
}
