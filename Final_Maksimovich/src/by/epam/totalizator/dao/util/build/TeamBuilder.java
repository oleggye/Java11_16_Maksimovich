package by.epam.totalizator.dao.util.build;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;

public final class TeamBuilder {

	private Team team = new Team();

	public Team getTeam() {
		return team;
	}

	public void buildTeam(Club club, Country country, Sport sport) {
		team.setClub(club);
		team.setCountry(country);
		team.setSport(sport);
	}

}
