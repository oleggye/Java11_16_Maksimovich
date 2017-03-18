package by.epam.totalizator.dao.util.sql;

public final class UserSQLStore {
	
	public static final String GET_USER_BY_ID_SQL =
			"select first_name, " +
					"last_name, " +
					"date_of_birth, " +
					"email, " + 
					"country.name as country, " +
					"city, " +
					"code, " +
					"phone_number, " +
					"currency, " +
					"balance, " +
					"locale " +
			"from totalizator.user " +
			"join totalizator.country " + 
			"on user.id_country = country.id " +
			"where user.id = ?;";

	public static final String SIGN_IN_USER_SQL = 
			"select user.id, user.user_type, user.locale from totalizator.user "
			+ "join totalizator.user_private "
			+ "on user.id = user_private.id_user "
			+ "where user.email = ? "
			+ "	and user_private.password = (SELECT SHA2(?, 256));";
	
	public static final String GET_USER_ID_BY_EMAIL_SQL = 
			"select user.id from totalizator.user "
			+ "where user.email = ?;";
}
