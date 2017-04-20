package by.epam.totalizator.bean;

public enum UserType {

	CLIENT("C"), BOOKMAKER("B"), ADMINISTRATOR("A");

	private String shortName;

	private UserType(String shortName) {
		this.shortName = shortName;
	}

	public static UserType getTypeByShortName(String shortName) {
		switch (shortName) {
		case "C":
			return UserType.CLIENT;
		case "B":
			return UserType.BOOKMAKER;
		case "A":
			return UserType.ADMINISTRATOR;
		default:
			throw new IllegalArgumentException();
		}
	}

	public String getShortName() {
		return this.shortName;
	}
}
