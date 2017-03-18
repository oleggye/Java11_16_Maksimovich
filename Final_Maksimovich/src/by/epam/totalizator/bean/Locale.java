package by.epam.totalizator.bean;

public enum Locale {

	en_EN("EN"), ru_RU("RU");

	private String shortName;

	private Locale(String shortName) {
		this.shortName = shortName;
	}

	public static Locale getLocaleByShortName(String shortName) {
		switch (shortName) {
		case "EN":
			return Locale.en_EN;
		case "RU":
			return Locale.ru_RU;
		default:
			throw new IllegalArgumentException();
		}
	}

	public String getShortName() {
		return this.shortName;
	}
}
