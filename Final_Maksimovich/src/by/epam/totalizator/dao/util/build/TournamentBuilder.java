package by.epam.totalizator.dao.util.build;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Tournament;

public final class TournamentBuilder {

	private Tournament tournament = new Tournament();

	public Tournament getTournament() {
		return tournament;
	}

	public void buildTournament(int id, String name, Sport sport) {

		tournament.setId(id);
		tournament.setName(name);
		tournament.setSport(sport);
	}
}
