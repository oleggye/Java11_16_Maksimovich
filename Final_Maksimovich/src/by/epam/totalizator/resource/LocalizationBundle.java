package by.epam.totalizator.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationBundle {

	private static final LocalizationBundle instance = new LocalizationBundle();
	private ResourceBundle resourceBundle;

	private static final String baseName = "localization.local";

	private LocalizationBundle() {
	}

	public static LocalizationBundle getInstance() {
		return instance;
	}

	public String getProperty(Locale locale, String key) {
		resourceBundle = ResourceBundle.getBundle(baseName, locale);
		return resourceBundle.getString(key);
	}

}
