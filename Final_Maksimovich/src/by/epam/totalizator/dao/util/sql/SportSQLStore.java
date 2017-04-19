package by.epam.totalizator.dao.util.sql;

/**
 * Class contains strings with querier for {@link #Sport}
 */
public final class SportSQLStore {

	public static final String GET_SPORT_LIST_EN = 
			"SELECT sport.id, sport.name FROM totalizator.sport;";

	public static final String GET_SPORT_LIST_RU = 
			"SELECT sport.id, sport.name_RU FROM totalizator.sport;";

}
