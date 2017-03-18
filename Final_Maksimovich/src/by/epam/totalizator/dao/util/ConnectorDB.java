package by.epam.totalizator.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectorDB {

	private static boolean isDriverRegistered = false;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		ResourceBundle resource = ResourceBundle.getBundle("by.epam.totalizator.dao.util.db", Locale.ENGLISH);
		String url = resource.getString("db.url");
		String user = resource.getString("db.user");
		String password = resource.getString("db.password");

		if (!isDriverRegistered) {
			String driver = resource.getString("db.driver");
			Class.forName(driver);
			isDriverRegistered = true;
		}

		return DriverManager.getConnection(url, user, password);
	}
}
