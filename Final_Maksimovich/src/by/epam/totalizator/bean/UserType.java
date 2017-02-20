package by.epam.totalizator.bean;

public enum UserType {

	CLIENT('C'), ADMINISTRATOR('A');

	private char shortName;

	private UserType(char shortName) {
		this.shortName = shortName;
	}

	public static UserType getTypeByShortName(char shortName) {
		switch (shortName) {
		case 'C':
			return UserType.CLIENT;
		case 'A':
			return UserType.ADMINISTRATOR;
		default:
			throw new IllegalArgumentException();
		}
	}

	public char getShortName() {
		return this.shortName;
	}
}
