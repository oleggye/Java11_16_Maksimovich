package by.epam.totalizator.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

@WebListener
public class ConnectionProviderListener implements ServletContextListener {

	private IConnectionPool connectionPool;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		connectionPool = ConnectionPoolImpl.getInstance();
		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
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
