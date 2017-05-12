package by.epam.totalizator.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.dao.connection.ConnectionProvider;
import by.epam.totalizator.dao.factory.ConnectionFactory;

@WebListener
public class ConnectionProviderListener implements ServletContextListener {

	private static final Logger LOGGER = LogManager.getLogger(ConnectionProviderListener.class.getName());

	private ConnectionProvider connectionFactory;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		connectionFactory = ConnectionFactory.getInstance().getConnectionProvider();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		if (connectionFactory != null) {
			try {
				connectionFactory.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARN, e);
			}
		}
	}
}