package by.epam.totalizator.dao.util.sql;

public final class CompetitionSQLStrore {

	public static final String GET_COMPETITION_BY_ID_EN = "select competition.start_time, "
			+ "sport.id as sport_id, sport.name as sport_title,"
			+ "tournament.id as tournament_id, tournament.name as tournament_title,"
			+ "competition.home_team as home_id, (select club.name from club where club.id = competition.home_team) as home_team,"
			+ "competition.away_team as away_id, (select club.name from club where club.id = competition.away_team) as away_team,"
			+ "country.id as country_id, country.name as country,"
			+ "competition.win_home_rate, competition.draw_rate, competition.win_away_rate, competition.result "
			+ "from totalizator.competition "
			+ "join totalizator.tournament on competition.id_tournament = tournament.id "
			+ "join totalizator.sport on competition.id_sport = sport.id "
			+ "join totalizator.country on competition.id_country = country.id "
			+ "where competition.id = ? and competition.status='A';";

	public static final String GET_COMPETITION_BY_ID_RU = "select competition.start_time, "
			+ "sport.id as sport_id, sport.name_ru as sport_title,"
			+ "tournament.id as tournament_id, tournament.name_ru as tournament_title,"
			+ "competition.home_team as home_id, (select club.name_ru from club where club.id = competition.home_team) as home_team,"
			+ "competition.away_team as away_id, (select club.name_ru from club where club.id = competition.away_team) as away_team,"
			+ "country.id as country_id, country.name_ru as country,"
			+ "competition.win_home_rate, competition.draw_rate, competition.win_away_rate, competition.result "
			+ "from totalizator.competition "
			+ "join totalizator.tournament on competition.id_tournament = tournament.id "
			+ "join totalizator.sport on competition.id_sport = sport.id "
			+ "join totalizator.country on competition.id_country = country.id "
			+ "where competition.id = ? and competition.status='A';";

	public static final String ADD_COMPETITION = "INSERT INTO `totalizator`.`competition` "
			+ "(`id_sport`, `id_tournament`, `home_team`," + " `away_team`, `id_country`, `start_time`) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";

	public static final String UPDATE_COMPETITION = "UPDATE `totalizator`.`competition` "
			+ "SET `id_sport`=?, `id_tournament`=?, `home_team`=?, "
			+ "`away_team`=?, `id_country`=?, `start_time`=?, `result`=? " + "WHERE `id`=?;";

	public static final String UPDATE_All_COMPETITION_RATE = "UPDATE `totalizator`.`competition` "
			+ "SET `win_home_rate`=?, " + "`draw_rate`=?," + " `win_away_rate`=?, " + "is_prepared='1' "
			+ " WHERE `id`=?;";

	public static final String CHANGE_COMPETITION_STATUS = "UPDATE `totalizator`.`competition` SET `status`='D' WHERE `id`=?;";

	public static final String GET_COMPETITION_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where competition.status='A';";

	public static final String GET_LIVE_COMPETITION_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where competition.start_time <= Now() and competition.is_prepared=? and competition.status='A';";

	public static final String GET_AVAILABLE_COMPETITION_LIST_COUNT = "select count(*) "
			+ "from totalizator.competition " + "where competition.start_time > Now() and competition.status='A';";

	public static final String GET_PREPARED_AVAILABLE_COMPETITION_LIST_COUNT = "select count(*) "
			+ "from totalizator.competition "
			+ "where competition.start_time > Now() and competition.status='A' and competition.is_prepared='1';";

	public static final String GET_COMPETITION_RESULT_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where result is not null and competition.status='A';";

	public static final String GET_SPECIAL_COMPETITION_LIST_COUNT = "select count(*) " + "from totalizator.competition "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.status='A';";

	public static final String GET_PREPARED_SPECIAL_COMPETITION_LIST_COUNT = "select count(*) "
			+ "from totalizator.competition "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.is_prepared='1' and competition.status='A';";

	public static final String GET_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.id, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate, "
			+ "competition.result " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.id, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate, " + "competition.result " + "from totalizator.competition "
			+ "join totalizator.tournament " + "on competition.id_tournament = tournament.id "
			+ "join totalizator.sport " + "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_AVAILABLE_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.start_time > NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_AVAILABLE_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.start_time > NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_PREPARED_AVAILABLE_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.result is null and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

			//+ "where competition.start_time > NOW() and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_PREPARED_AVAILABLE_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.result is null and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

			//+ "where competition.start_time > NOW() and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_LIVE_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.start_time <= NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_LIVE_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.start_time <= NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_SPECIAL_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_SPECIAL_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_PREPARED_SPECIAL_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate "
			+ "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.result is null and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

			//+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_PREPARED_SPECIAL_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.result is null and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

			//+ "where competition.id_sport = ? and competition.id_tournament = ? and competition.start_time > NOW() and competition.is_prepared='1' and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_RESULT_COMPETITION_LIST_EN = "select competition.id, competition.start_time, sport.name as sport_title,tournament.name as tournament_title, "
			+ "(select club.name from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name from club where club.id = competition.away_team) as away_team, " + "country.name, "
			+ "competition.win_home_rate, " + "competition.draw_rate, " + "competition.win_away_rate,"
			+ "competition.result " + "from totalizator.competition " + "join totalizator.tournament "
			+ "on competition.id_tournament = tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.result is not null and competition.status='A' order by competition.start_time desc Limit ?,?;";

	public static final String GET_RESULT_COMPETITION_LIST_RU = "select competition.id, competition.start_time, sport.name_ru as sport_title,tournament.name_ru as tournament_title, "
			+ "(select club.name_ru from club where club.id = competition.home_team) as home_team, "
			+ "(select club.name_ru from club where club.id = competition.away_team) as away_team, "
			+ "country.name_ru, " + "competition.win_home_rate, " + "competition.draw_rate, "
			+ "competition.win_away_rate," + "competition.result " + "from totalizator.competition "
			+ "join totalizator.tournament " + "on competition.id_tournament = tournament.id "
			+ "join totalizator.sport " + "on competition.id_sport = sport.id " + "join totalizator.country "
			+ "on competition.id_country = country.id "
			+ "where competition.result is not null and competition.status='A' order by competition.start_time desc Limit ?,?;";
}
