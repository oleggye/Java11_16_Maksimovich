package by.epam.totalizator.util.build;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;

public final class TeamBuilder {

	private Club club;
	private Country country;
	private Sport sport;

	public Team build() {
		Team team = new Team();
		team.setClub(club);
		team.setCountry(country);
		team.setSport(sport);
		return team;
	}

	public TeamBuilder buildClub(Club club) {
		this.club = club;
		return this;
	}

	public TeamBuilder buildCountry(Country country) {
		this.country = country;
		return this;
	}

	public TeamBuilder buildSport(Sport sport) {
		this.sport = sport;
		return this;
	}
}
