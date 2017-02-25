package by.epam.totalizator.bean;

public enum BetType {

	HOME_WIN('H'), DRAW('D'), AWAY_WIN('A');

	private char shortName;

	private BetType(char shortName) {
		this.shortName = shortName;
	}

	public static BetType getTypeByShortName(char shortName) {
		switch (shortName) {
		case 'H':
			return BetType.HOME_WIN;
		case 'D':
			return BetType.DRAW;
		case 'A':
			return BetType.AWAY_WIN;
		default:
			throw new IllegalArgumentException();
		}
	}

	public char getShortName() {
		return this.shortName;
	}
}
