package by.epam.totalizator.dao.util.sql;

public final class TeamSQLStore {
	
	public static final String GET_TEAM_LIST_BY_ID_SPORT_EN = 
			"SELECT club.id, club.name, "
					+ "country.id, country.name "
					+ "FROM totalizator.team "
					+ "inner join totalizator.club "
					+ "on team.id_club = club.id "
					+ "inner join totalizator.country "
					+ "on team.id_country = country.id where team.id_sport= ?;";
	
	public static final String GET_TEAM_LIST_BY_ID_SPORT_RU = 
			"SELECT club.id, club.name_ru, "
					+ "country.id, country.name_ru "
					+ "FROM totalizator.team "
					+ "inner join totalizator.club "
					+ "on team.id_club = club.id "
					+ "inner join totalizator.country "
					+ "on team.id_country = country.id where team.id_sport= ?;";
}
