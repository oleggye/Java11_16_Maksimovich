package by.epam.totalizator.dao.util.sql;

/**
 * Class contains strings with querier for {@link #User}
 */
public final class UserSQLStore {

	public static final String ADD_NEW_USER = "INSERT INTO `totalizator`.`user` "
			+ "(`first_name`, `last_name`, `date_of_birth`, `email`, "
			+ "`id_country`, `city`, `code`, `phone_number`, `currency`, `locale`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String GET_USER_BY_ID_SQL_EN = "select first_name, " + "last_name, " + "date_of_birth, "
			+ "email, " + "country.name as country, " + "city, " + "code, " + "phone_number, " + "currency, "
			+ "balance, " + "locale, is_banned " + "from totalizator.user " + "join totalizator.country "
			+ "on user.id_country = country.id " + "where user.id = ?;";

	public static final String GET_USER_BY_ID_SQL_RU = "select first_name, " + "last_name, " + "date_of_birth, "
			+ "email, " + "country.name_ru as country, " + "city, " + "code, " + "phone_number, " + "currency, "
			+ "balance, " + "locale, is_banned " + "from totalizator.user " + "join totalizator.country "
			+ "on user.id_country = country.id " + "where user.id = ?;";

	public static final String INSERT_INTO_USER_SQL = "INSERT INTO `totalizator`.`user`"
			+ "(`first_name`, `last_name`, `date_of_birth`," + "`email`, `id_country`, `city`, `code`,"
			+ "`phone_number`, `currency`, `registration_time`, `locale`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String GET_LAST_INSERTED_ID = "SELECT LAST_INSERT_ID();";

	public static final String INSERT_INTO_USER_PRIVATE_SQL = "INSERT INTO `totalizator`.`user_private` "
			+ "(`id_user`, `id_sec_question`, `answer`, `password`) " + "VALUES (?, ?," + "(SELECT SHA2(?, 256)),"
			+ "(SELECT SHA2(?, 256)));";

	public static final String SIGN_IN_USER_SQL = "select user.id, user.user_type, user.balance, user.currency, user.locale, user.is_banned from totalizator.user "
			+ "join totalizator.user_private " + "on user.id = user_private.id_user " + "where user.email = ? "
			+ "	and user_private.password = (SELECT SHA2(?, 256));";

	public static final String GET_USER_ID_BY_EMAIL_SQL = "select user.id from totalizator.user "
			+ "where user.email = ?;";

	public static final String GET_USER_LIST_COUNT = "select count(*) from totalizator.user";

	public static final String GET_USER_LIST_EN = "SELECT user.id, user.first_name,user.last_name, user.date_of_birth, user.email,"
			+ "user.id_country, country.name, user.city, user.code,"
			+ "user.phone_number, user.currency, user.balance, user.user_type,"
			+ "user.registration_time, user.locale, user.is_banned, user.status FROM totalizator.user "
			+ "join totalizator.country on user.id_country = country.id order by user.id asc Limit ?,?;";

	public static final String GET_USER_LIST_RU = "SELECT user.id, user.first_name,user.last_name, user.date_of_birth, user.email,"
			+ "user.id_country, country.name_ru, user.city, user.code,"
			+ "user.phone_number, user.currency, user.balance, user.user_type,"
			+ "user.registration_time, user.locale, user.is_banned, user.status FROM totalizator.user "
			+ "join totalizator.country on user.id_country = country.id order by user.id asc Limit ?,?;";

	public static final String BAN_USER = "UPDATE `totalizator`.`user` SET `is_banned`='1' WHERE `id`=?;";

	public static final String UN_BAN_USER = "UPDATE `totalizator`.`user` SET `is_banned`='0' WHERE `id`=?;";

	public static final String UPDATE_USER_TYPE = "UPDATE `totalizator`.`user` SET `user_type`=? WHERE `id`=?;";

	public static final String CALL_CHANGE_USER_BALANCE = "{call totalizator.increase_user_balance(?, ?)}";
}
