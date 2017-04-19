package by.epam.totalizator.dao.util.sql;

/**
 * Class contains strings with querier for {@link #Tournament}
 */
public final class TournamentSQLStore {

	public static final String GET_TOURNAMENT_LIST_BY_ID_SPORT_EN =
			"SELECT tournament.id, tournament.name "
			+ "FROM totalizator.tournament "
			+ "where tournament.id_sport= ?;";
	
	public static final String GET_TOURNAMENT_LIST_BY_ID_SPORT_RU =
			"SELECT tournament.id, tournament.name_ru "
			+ "FROM totalizator.tournament "
			+ "where tournament.id_sport= ?;";
}
