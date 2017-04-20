package by.epam.totalizator.bean.build;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Tournament;

/**
 * 
 * Realization of builder pattern for Tournament bean {@link Tournament}
 *
 */
public final class TournamentBuilder {

	private int id;
	private String name;
	private Sport sport;

	public Tournament build() {
		Tournament tournament = new Tournament();
		tournament.setId(id);
		tournament.setName(name);
		tournament.setSport(sport);
		return tournament;
	}

	public TournamentBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public TournamentBuilder buildName(String name) {
		this.name = name;
		return this;
	}

	public TournamentBuilder buildSport(Sport sport) {
		this.sport = sport;
		return this;
	}
}
