package by.epam.totalizator.dao.util.sql;

public class TournamentSQLStore {

	public static final String GET_TOURNAMENT_LIST_BY_ID_SPORT =
			"SELECT tournament.id, tournament.name FROM totalizator.tournament where tournament.id_sport= ?;";
}
