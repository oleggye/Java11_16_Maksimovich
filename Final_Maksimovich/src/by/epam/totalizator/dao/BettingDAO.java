package by.epam.totalizator.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.dao.exception.DAOException;

public interface BettingDAO {

	public void makeBetting(Betting betting) throws DAOException;;

	public Betting getBettingInfo(int id) throws DAOException;;

	public void cancelBetting(int id) throws DAOException;

	public List<Betting> obtainUserBettingList(int userId, int fromRecord, int recordCount, Locale locale)
			throws DAOException;

	public int obtainUserBettingCount(int IdUser) throws DAOException;

	public List<Betting> obtainBettingListForComeptition(int idCompetition) throws DAOException;

	public void defineBettingGain(int idBetting, BigDecimal gain) throws DAOException;
}
