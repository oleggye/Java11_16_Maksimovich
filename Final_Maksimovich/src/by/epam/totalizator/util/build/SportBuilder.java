package by.epam.totalizator.util.build;

import by.epam.totalizator.bean.Sport;

public final class SportBuilder {

	private int id;
	private String name;

	public Sport build() {
		Sport sport = new Sport();
		sport.setId(id);
		sport.setName(name);
		return sport;
	}

	public SportBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public SportBuilder buildName(String name) {
		this.name = name;
		return this;
	}

}
