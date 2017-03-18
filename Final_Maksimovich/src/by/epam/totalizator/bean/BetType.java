package by.epam.totalizator.bean;

public enum BetType {

	HOME_WIN("H"), DRAW("D"), AWAY_WIN("A");

	private String shortName;

	private BetType(String shortName) {
		this.shortName = shortName;
	}

	public static BetType getTypeByShortName(String shortName) {
		switch (shortName) {
		case "H":
			return BetType.HOME_WIN;
		case "D":
			return BetType.DRAW;
		case "A":
			return BetType.AWAY_WIN;
		default:
			throw new IllegalArgumentException();
		}
	}

	public String getShortName() {
		return this.shortName;
	}
}
