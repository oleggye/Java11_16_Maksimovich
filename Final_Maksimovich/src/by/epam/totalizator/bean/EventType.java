package by.epam.totalizator.bean;

public enum EventType {

	HOME_WIN("H"), DRAW("D"), AWAY_WIN("A");

	private String shortName;

	private EventType(String shortName) {
		this.shortName = shortName;
	}

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

	public String getShortName() {
		return this.shortName;
	}
}
