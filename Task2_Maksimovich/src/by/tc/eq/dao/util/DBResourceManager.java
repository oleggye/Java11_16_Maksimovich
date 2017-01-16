package by.tc.eq.dao.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBResourceManager {

	private final static DBResourceManager instance = new DBResourceManager();

	private ResourceBundle bundle = ResourceBundle.getBundle("by.tc.eq.dao.util.db", Locale.ENGLISH);

	private DBResourceManager() {

	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}

}
