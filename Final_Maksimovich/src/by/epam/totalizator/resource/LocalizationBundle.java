package by.epam.totalizator.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Bundle for localized error messages
 */
public class LocalizationBundle {

	private static final String BASE_NAME = "localization.local";

	private LocalizationBundle() {
	}

	public static String getProperty(Locale locale, String key) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
		return resourceBundle.getString(key);
	}
}