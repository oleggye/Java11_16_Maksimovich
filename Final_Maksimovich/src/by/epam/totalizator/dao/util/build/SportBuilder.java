package by.epam.totalizator.dao.util.build;

import by.epam.totalizator.bean.Sport;

public final class SportBuilder {

	private Sport sport = new Sport();

	public Sport getSport() {
		return sport;
	}

	public void buildSport(int id, String name) {
		sport.setId(id);
		sport.setName(name);
	}

}
