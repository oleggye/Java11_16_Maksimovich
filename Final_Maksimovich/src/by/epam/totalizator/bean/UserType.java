package by.epam.totalizator.bean;

public enum UserType {

	CLIENT("C"), BOOKMAKER("B"), ADMINISTRATOR("A");

	/**
	 * a unique value which is used for convenient representation of a
	 * {@link UserType} instance
	 */
	private String shortName;

	private UserType(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Method takes an instance of {@link UserType} by its short name
	 * 
	 * @param shortName
	 *            a unique value for the instance of {@link UserType}
	 * 
	 *            <ul>
	 *            <li>CLIENT <-> "C"</li>
	 *            <li>BOOKMAKER <-> "B"</li>
	 *            <li>ADMINISTRATOR <-> "A"</li>
	 *            </ul>
	 * @return an instance of {@link UserType}
	 */
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

	/**
	 * Method takes a unique {@link #shortName}
	 * 
	 * @return {@link #shortName}
	 */
	public String getShortName() {
		return this.shortName;
	}
}