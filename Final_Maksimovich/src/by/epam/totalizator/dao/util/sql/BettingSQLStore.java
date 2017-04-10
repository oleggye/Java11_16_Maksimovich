package by.epam.totalizator.dao.util.sql;

public final class BettingSQLStore {

	public static final String ADD_BETTING = "INSERT INTO `totalizator`.`betting` "
			+ "(`id_competition`, `id_client`, `bet_type`, `bet_rate`, `bet_size`) VALUES (?, ?, ?, ?, ?);";

	public static final String GET_USER_BETTING_LIST_EN = "select betting.id, " + "betting.bet_time,"
			+ "sport.name as sport," + "tournament.name as tournament,"
			+ "(select club.name from club where club.id=competition.home_team)as home_team,"
			+ "(select club.name from club where club.id=competition.away_team)as away_team," + "betting.bet_type,"
			+ "betting.bet_size," + "betting.bet_rate as coefficient," + "betting.gain," + "user.locale "
			+ "from totalizator.betting " + "join totalizator.competition "
			+ "on betting.id_competition= competition.id " + "join totalizator.tournament "
			+ "on competition.id_tournament= tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport= sport.id " + "join totalizator.user " + "on betting.id_client = user.id "
			+ "where betting.id_client=? " + "and betting.status='A' order by betting.bet_time desc Limit ?,?;";

	public static final String GET_USER_BETTING_LIST_RU = "select betting.id, " + "betting.bet_time,"
			+ "sport.name_ru as sport," + "tournament.name_ru as tournament,"
			+ "(select club.name_ru from club where club.id=competition.home_team)as home_team,"
			+ "(select club.name_ru from club where club.id=competition.away_team)as away_team," + "betting.bet_type,"
			+ "betting.bet_size," + "betting.bet_rate as coefficient," + "betting.gain," + "user.locale "
			+ "from totalizator.betting " + "join totalizator.competition "
			+ "on betting.id_competition= competition.id " + "join totalizator.tournament "
			+ "on competition.id_tournament= tournament.id " + "join totalizator.sport "
			+ "on competition.id_sport= sport.id " + "join totalizator.user " + "on betting.id_client = user.id "
			+ "where betting.id_client=? " + "and betting.status='A' order by betting.bet_time desc Limit ?,?;";

	public static final String GET_USER_BETTING_COUNT = "select count(*) from totalizator.betting "
			+ "where betting.id_client=? " + "and betting.status='A';";

	public static final String GET_BETTING_LIST_BY_ID_COMPETITION = "select id,id_competition, id_client, bet_type, bet_rate,"
			+ " bet_size from totalizator.betting where betting.id_competition = ?;";

	public static final String SET_BETTING_GAIN = "UPDATE `totalizator`.`betting` SET `gain`=? WHERE `id`=?;";
}
