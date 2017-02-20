package by.epam.totalizator.bean;

public enum Result {

	HOME_WIN('H'), DRAW('D'), AWAY_WIN('A');

	private char shortName;

	private Result(char shortName) {
		this.shortName = shortName;
	}

	public static Result getTypeByShortName(char shortName) {
		switch (shortName) {
		case 'H':
			return Result.HOME_WIN;
		case 'D':
			return Result.DRAW;
		case 'A':
			return Result.AWAY_WIN;
		default:
			throw new IllegalArgumentException();
		}
	}

	public char getShortName() {
		return this.shortName;
	}
}
