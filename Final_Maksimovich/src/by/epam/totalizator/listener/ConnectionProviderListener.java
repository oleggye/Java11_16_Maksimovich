package by.epam.totalizator.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

@WebListener
public class ConnectionProviderListener implements ServletContextListener {
	
	private static final Logger LOGGER = LogManager.getLogger(ConnectionProviderListener.class.getName());

	private IConnectionPool connectionPool;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		connectionPool = ConnectionPoolImpl.getInstance();
		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
			LOGGER.log(Level.WARN, e);
			throw new RuntimeException("Connection pool init error.");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		if (connectionPool != null) {
			connectionPool.dispose();
		}
	}
}
