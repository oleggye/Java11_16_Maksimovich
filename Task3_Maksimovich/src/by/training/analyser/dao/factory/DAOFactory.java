package by.training.analyser.dao.factory;

import by.training.analyser.dao.XmlDAO;
import by.training.analyser.dao.impl.XmlDAOImpl;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private XmlDAO xmlDAO = new XmlDAOImpl();

	private DAOFactory() {
	}

	public XmlDAO getXmlDAO() {
		return xmlDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
