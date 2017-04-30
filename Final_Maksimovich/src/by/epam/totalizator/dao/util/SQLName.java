package by.epam.totalizator.dao.util;

/**
 * Names of sql-queries
 *
 */
public enum SQLName {

	/* BettingSQL names */
	ADD_BETTING,

	GET_USER_BETTING_LIST_EN, GET_USER_BETTING_LIST_RU, GET_USER_BETTING_COUNT,

	GET_BETTING_LIST_BY_ID_COMPETITION, SET_BETTING_GAIN, CHANGE_BETTING_STATUS,

	/* CompetitionSQL names */
	GET_COMPETITION_BY_ID_EN, GET_COMPETITION_BY_ID_RU,

	ADD_COMPETITION, UPDATE_COMPETITION, UPDATE_All_COMPETITION_RATE, CHANGE_COMPETITION_STATUS,

	GET_COMPETITION_LIST_EN, GET_COMPETITION_LIST_RU, GET_COMPETITION_LIST_COUNT,

	GET_AVAILABLE_COMPETITION_LIST_EN, GET_AVAILABLE_COMPETITION_LIST_RU, GET_AVAILABLE_COMPETITION_LIST_COUNT,

	GET_PREPARED_AVAILABLE_COMPETITION_LIST_EN, GET_PREPARED_AVAILABLE_COMPETITION_LIST_RU, GET_PREPARED_AVAILABLE_COMPETITION_LIST_COUNT,

	GET_LIVE_COMPETITION_LIST_EN, GET_LIVE_COMPETITION_LIST_RU, GET_LIVE_COMPETITION_LIST_COUNT,

	GET_SPECIAL_COMPETITION_LIST_EN, GET_SPECIAL_COMPETITION_LIST_RU, GET_SPECIAL_COMPETITION_LIST_COUNT,

	GET_PREPARED_SPECIAL_COMPETITION_LIST_EN, GET_PREPARED_SPECIAL_COMPETITION_LIST_RU, GET_PREPARED_SPECIAL_COMPETITION_LIST_COUNT,

	GET_RESULT_COMPETITION_LIST_EN, GET_RESULT_COMPETITION_LIST_RU, GET_COMPETITION_RESULT_LIST_COUNT,

	/* CountrySQL names */
	GET_COUNTRY_LIST_EN, GET_COUNTRY_LIST_RU,

	/* TeamSQL names */
	GET_TEAM_LIST_BY_ID_SPORT_EN, GET_TEAM_LIST_BY_ID_SPORT_RU,

	/* TournamentSQL names */
	GET_TOURNAMENT_LIST_BY_ID_SPORT_EN, GET_TOURNAMENT_LIST_BY_ID_SPORT_RU,

	/* UserSQL names */
	ADD_NEW_USER,

	GET_USER_BY_ID_SQL_EN, GET_USER_BY_ID_SQL_RU, GET_USER_ID_BY_EMAIL_SQL,

	INSERT_INTO_USER_SQL, INSERT_INTO_USER_PRIVATE_SQL,

	GET_LAST_INSERTED_ID, SIGN_IN_USER_SQL,

	GET_USER_LIST_EN, GET_USER_LIST_RU, GET_USER_LIST_COUNT,

	BAN_USER, UNBAN_USER, UPDATE_USER_TYPE, CALL_CHANGE_USER_BALANCE,

	/* SportSQL names */
	GET_SPORT_LIST_EN, GET_SPORT_LIST_RU

}