package by.epam.totalizator.bean.build;

import by.epam.totalizator.bean.Club;

/**
 * 
 * Realization of builder pattern for Club bean
 * {@link Club}
 *
 */

public final class ClubBuilder {

	private int id;
	private String name;

	public Club build() {

		Club club = new Club();
		club.setId(id);
		club.setName(name);

		return club;
	}

	public ClubBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public ClubBuilder buildName(String name) {
		this.name = name;
		return this;
	}
}
