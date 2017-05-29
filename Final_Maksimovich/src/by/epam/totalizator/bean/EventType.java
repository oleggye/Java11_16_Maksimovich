package by.epam.totalizator.bean;

public enum EventType {

	HOME_WIN("H"), DRAW("D"), AWAY_WIN("A");

	/**
	 * a unique value which is used for convenient representation of a
	 * {@link EventType} instance
	 */
	private String shortName;

	private EventType(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Method takes an instance of {@link EventType} by its short name
	 * 
	 * @param shortName
	 *            a unique value for the instance of {@link EventType}
	 * 
	 *            <ul>
	 *            <li>HOME_WIN <-> "H"</li>
	 *            <li>DRAW <-> "D"</li>
	 *            <li>AWAY_WIN <-> "A"</li>
	 *            </ul>
	 * @return an instance of {@link EventType}
	 */

	public static EventType getTypeByShortName(String shortName) {
		switch (shortName) {
		case "H":
			return EventType.HOME_WIN;
		case "D":
			return EventType.DRAW;
		case "A":
			return EventType.AWAY_WIN;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method takes a unique {@link #shortName}
	 * 
	 * @return
	 */
	public String getShortName() {
		return this.shortName;
	}
}