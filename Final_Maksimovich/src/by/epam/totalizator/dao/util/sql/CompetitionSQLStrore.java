package by.epam.totalizator.dao.util.sql;

public final class CompetitionSQLStrore {
	
	public static final String GET_LIVE_COMPETITIONS_COUNT = 
			"select count(*) " +
				"from totalizator.competition " +
				"where competition.start_time <= Now();";

	public static final String GET_AVAILABLE_COMPETITIONS_COUNT = 
			"select count(*) " +
				"from totalizator.competition " +
				"where competition.start_time > Now();";

	public static final String GET_AVAILABLE_COMPETITIONS = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.start_time > NOW() Limit ?,?;";

	public static final String GET_LIVE_COMPETITIONS = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.start_time <= NOW() Limit ?,?;";
}
