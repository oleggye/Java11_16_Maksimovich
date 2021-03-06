package by.epam.totalizator.controller.command;

/**
 * 
 * Enum constants for available commands
 *
 */

public enum CommandName {

	CHANGE_LOCALE,

	SIGNUP_PAGE, SIGNUP, SIGNIN_PAGE, SIGNIN, SIGN_OUT,

	HOME, RESULT, TOURNAMENT_PAGE,

	GET_USER_LIST, BAN_USER, UNBAN_USER,

	CREATE_COMPETITION, UPDATE_COMPETITION, DELETE_COMPETITION,

	COMPETITION_INFO, DATA_FOR_COMPETITION,

	EARNING_MANAGEMENT,

	COMPETITION_MANAGEMENT, USER_MANAGEMENT,

	CHANGE_USER_TYPE, CHANGE_USER_BALANCE,

	SET_RATES, MAKE_BETTING, ADD_COMPETITION,

	USER_PROFILE, USER_BETTING, USER_BALANCE,

	WRONGREQUEST;
}
