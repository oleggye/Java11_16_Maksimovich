package by.epam.totalizator.resource;

import java.util.ResourceBundle;

/**
 * Bundle for db parameters
 */
public class ConfigurationManager {

	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.config");

	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}