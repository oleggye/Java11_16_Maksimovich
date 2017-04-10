package by.epam.totalizator.dao.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.dao.util.sql.BettingSQLStore;
import by.epam.totalizator.dao.util.sql.CompetitionSQLStrore;
import by.epam.totalizator.dao.util.sql.CountrySQLStore;
import by.epam.totalizator.dao.util.sql.SportSQLStore;
import by.epam.totalizator.dao.util.sql.TeamSQLStore;
import by.epam.totalizator.dao.util.sql.TournamentSQLStore;
import by.epam.totalizator.dao.util.sql.UserSQLStore;

public class SQLProvider {

	private static final Logger logger = LogManager.getLogger(SQLProvider.class.getName());

	private static final SQLProvider instance = new SQLProvider();

	private Map<SQLName, String> sqlRepository = new HashMap<>();

	public static SQLProvider getInstance() {
		return instance;
	}

	private SQLProvider() {
		/* BettingSQL */
		sqlRepository.put(SQLName.ADD_BETTING, BettingSQLStore.ADD_BETTING);
		sqlRepository.put(SQLName.GET_USER_BETTING_LIST_EN, BettingSQLStore.GET_USER_BETTING_LIST_EN);
		sqlRepository.put(SQLName.GET_USER_BETTING_LIST_RU, BettingSQLStore.GET_USER_BETTING_LIST_RU);
		sqlRepository.put(SQLName.GET_USER_BETTING_COUNT, BettingSQLStore.GET_USER_BETTING_COUNT);
		sqlRepository.put(SQLName.GET_BETTING_LIST_BY_ID_COMPETITION,
				BettingSQLStore.GET_BETTING_LIST_BY_ID_COMPETITION);
		sqlRepository.put(SQLName.SET_BETTING_GAIN, BettingSQLStore.SET_BETTING_GAIN);

		/* CompetitionSQL */
		sqlRepository.put(SQLName.GET_COMPETITION_BY_ID_EN, CompetitionSQLStrore.GET_COMPETITION_BY_ID_EN);
		sqlRepository.put(SQLName.GET_COMPETITION_BY_ID_RU, CompetitionSQLStrore.GET_COMPETITION_BY_ID_RU);
		sqlRepository.put(SQLName.ADD_COMPETITION, CompetitionSQLStrore.ADD_COMPETITION);
		sqlRepository.put(SQLName.UPDATE_COMPETITION, CompetitionSQLStrore.UPDATE_COMPETITION);
		sqlRepository.put(SQLName.UPDATE_All_COMPETITION_RATE, CompetitionSQLStrore.UPDATE_All_COMPETITION_RATE);
		sqlRepository.put(SQLName.CHANGE_COMPETITION_STATUS, CompetitionSQLStrore.CHANGE_COMPETITION_STATUS);
		sqlRepository.put(SQLName.GET_COMPETITION_LIST_EN, CompetitionSQLStrore.GET_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_COMPETITION_LIST_RU, CompetitionSQLStrore.GET_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_COMPETITION_LIST_COUNT, CompetitionSQLStrore.GET_COMPETITION_LIST_COUNT);

		sqlRepository.put(SQLName.GET_AVAILABLE_COMPETITION_LIST_EN,
				CompetitionSQLStrore.GET_AVAILABLE_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_AVAILABLE_COMPETITION_LIST_RU,
				CompetitionSQLStrore.GET_AVAILABLE_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_AVAILABLE_COMPETITION_LIST_COUNT,
				CompetitionSQLStrore.GET_AVAILABLE_COMPETITION_LIST_COUNT);

		sqlRepository.put(SQLName.GET_PREPARED_AVAILABLE_COMPETITION_LIST_EN,
				CompetitionSQLStrore.GET_PREPARED_AVAILABLE_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_PREPARED_AVAILABLE_COMPETITION_LIST_RU,
				CompetitionSQLStrore.GET_PREPARED_AVAILABLE_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_PREPARED_AVAILABLE_COMPETITION_LIST_COUNT,
				CompetitionSQLStrore.GET_PREPARED_AVAILABLE_COMPETITION_LIST_COUNT);

		sqlRepository.put(SQLName.GET_LIVE_COMPETITION_LIST_EN, CompetitionSQLStrore.GET_LIVE_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_LIVE_COMPETITION_LIST_RU, CompetitionSQLStrore.GET_LIVE_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_LIVE_COMPETITION_LIST_COUNT,
				CompetitionSQLStrore.GET_LIVE_COMPETITION_LIST_COUNT);

		sqlRepository.put(SQLName.GET_SPECIAL_COMPETITION_LIST_EN,
				CompetitionSQLStrore.GET_SPECIAL_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_SPECIAL_COMPETITION_LIST_RU,
				CompetitionSQLStrore.GET_SPECIAL_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_SPECIAL_COMPETITION_LIST_COUNT,
				CompetitionSQLStrore.GET_SPECIAL_COMPETITION_LIST_COUNT);

		sqlRepository.put(SQLName.GET_PREPARED_SPECIAL_COMPETITION_LIST_EN,
				CompetitionSQLStrore.GET_PREPARED_SPECIAL_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_PREPARED_SPECIAL_COMPETITION_LIST_RU,
				CompetitionSQLStrore.GET_PREPARED_SPECIAL_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_PREPARED_SPECIAL_COMPETITION_LIST_COUNT,
				CompetitionSQLStrore.GET_PREPARED_SPECIAL_COMPETITION_LIST_COUNT);

		sqlRepository.put(SQLName.GET_RESULT_COMPETITION_LIST_EN, CompetitionSQLStrore.GET_RESULT_COMPETITION_LIST_EN);
		sqlRepository.put(SQLName.GET_RESULT_COMPETITION_LIST_RU, CompetitionSQLStrore.GET_RESULT_COMPETITION_LIST_RU);
		sqlRepository.put(SQLName.GET_COMPETITION_RESULT_LIST_COUNT,
				CompetitionSQLStrore.GET_COMPETITION_RESULT_LIST_COUNT);

		/* CountrySQL */
		sqlRepository.put(SQLName.GET_COUNTRY_LIST_EN, CountrySQLStore.GET_COUNTRY_LIST_EN);
		sqlRepository.put(SQLName.GET_COUNTRY_LIST_RU, CountrySQLStore.GET_COUNTRY_LIST_RU);

		/* TeamSQL */
		sqlRepository.put(SQLName.GET_TEAM_LIST_BY_ID_SPORT_EN, TeamSQLStore.GET_TEAM_LIST_BY_ID_SPORT_EN);
		sqlRepository.put(SQLName.GET_TEAM_LIST_BY_ID_SPORT_RU, TeamSQLStore.GET_TEAM_LIST_BY_ID_SPORT_RU);

		/* TournamentSQL */
		sqlRepository.put(SQLName.GET_TOURNAMENT_LIST_BY_ID_SPORT_EN,
				TournamentSQLStore.GET_TOURNAMENT_LIST_BY_ID_SPORT_EN);
		sqlRepository.put(SQLName.GET_TOURNAMENT_LIST_BY_ID_SPORT_RU,
				TournamentSQLStore.GET_TOURNAMENT_LIST_BY_ID_SPORT_RU);

		/* UserSQL */
		sqlRepository.put(SQLName.ADD_NEW_USER, UserSQLStore.ADD_NEW_USER);
		sqlRepository.put(SQLName.GET_USER_BY_ID_SQL_EN, UserSQLStore.GET_USER_BY_ID_SQL_EN);
		sqlRepository.put(SQLName.GET_USER_BY_ID_SQL_RU, UserSQLStore.GET_USER_BY_ID_SQL_RU);
		sqlRepository.put(SQLName.GET_USER_ID_BY_EMAIL_SQL, UserSQLStore.GET_USER_ID_BY_EMAIL_SQL);
		sqlRepository.put(SQLName.INSERT_INTO_USER_SQL, UserSQLStore.INSERT_INTO_USER_SQL);
		sqlRepository.put(SQLName.INSERT_INTO_USER_PRIVATE_SQL, UserSQLStore.INSERT_INTO_USER_PRIVATE_SQL);
		sqlRepository.put(SQLName.GET_LAST_INSERTED_ID, UserSQLStore.GET_LAST_INSERTED_ID);
		sqlRepository.put(SQLName.SIGN_IN_USER_SQL, UserSQLStore.SIGN_IN_USER_SQL);
		sqlRepository.put(SQLName.GET_USER_LIST_EN, UserSQLStore.GET_USER_LIST_EN);
		sqlRepository.put(SQLName.GET_USER_LIST_RU, UserSQLStore.GET_USER_LIST_RU);
		sqlRepository.put(SQLName.GET_USER_LIST_COUNT, UserSQLStore.GET_USER_LIST_COUNT);
		sqlRepository.put(SQLName.BAN_USER, UserSQLStore.BAN_USER);
		sqlRepository.put(SQLName.UNBAN_USER, UserSQLStore.UN_BAN_USER);

		/* SportSQL names */
		sqlRepository.put(SQLName.GET_SPORT_LIST_EN, SportSQLStore.GET_SPORT_LIST_EN);
		sqlRepository.put(SQLName.GET_SPORT_LIST_RU, SportSQLStore.GET_SPORT_LIST_RU);
	}

	public String getSql(SQLName sqlName) {

		String sqlString = null;

		if (sqlName != null) {
			try {
				sqlString = sqlRepository.get(sqlName);

			} catch (IllegalArgumentException | NullPointerException e) {
				logger.log(Level.WARN, e.getStackTrace());
			}
		}
		return sqlString;
	}

}
