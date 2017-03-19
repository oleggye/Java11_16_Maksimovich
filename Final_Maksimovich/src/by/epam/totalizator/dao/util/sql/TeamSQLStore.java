package by.epam.totalizator.dao.util.sql;

public class TeamSQLStore {
	
	public static final String GET_TEAM_LIST_BY_ID_SPORT = 
			"SELECT club.id, club.name, "
					+ "country.id, country.name "
					+ "FROM totalizator.team "
					+ "inner join totalizator.club "
					+ "on team.id_club = club.id "
					+ "inner join totalizator.country "
					+ "on team.id_country = country.id where team.id_sport= ?;";
}
