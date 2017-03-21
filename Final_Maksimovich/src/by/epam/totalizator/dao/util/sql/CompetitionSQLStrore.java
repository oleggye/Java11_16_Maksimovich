package by.epam.totalizator.dao.util.sql;

public final class CompetitionSQLStrore {
	
	public static final String GET_COMPETITION_BY_ID = "select competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.id = ? and competition.status='A';";

	public static final String ADD_COMPETITION = 
			"INSERT INTO `totalizator`.`competition` "
			+ "(`id_sport`, `id_tournament`, `home_team`,"
			+ " `away_team`, `id_country`, `start_time`) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";
	
	public static final String UPDATE_All_COMPETITION_RATE =
			"UPDATE `totalizator`.`competition` "
			+ "SET `win_home_rate`=?, "
				+ "`draw_rate`=?,"
				+ " `win_away_rate`=?"
				+ " WHERE `id`=?;";
	
	public static final String GET_COMPETITION_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where competition.status='A';";
	
	public static final String GET_LIVE_COMPETITION_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where competition.start_time <= Now() and competition.status='A';";

	public static final String GET_AVAILABLE_COMPETITION_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where competition.start_time > Now() and competition.status='A';";

	public static final String GET_COMPETITION_RESULT_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where result is not null and competition.status='A';";
	
	public static final String GET_COMPETITION_LIST = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate, "
			+ "competition.result "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_AVAILABLE_COMPETITION_LIST = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.start_time > NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_LIVE_COMPETITION_LIST = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.start_time <= NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_SPECIAL_COMPETITION_LIST = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_RESULT_COMPETITION_LIST = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate,"
			+ "competition.result " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id " + "where competition.result is not null and competition.status='A' order by competition.start_time desc Limit ?,?;";
}
