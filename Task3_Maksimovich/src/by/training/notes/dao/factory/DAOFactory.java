package by.training.notes.dao.factory;

import by.training.notes.dao.XmlDAO;
import by.training.notes.dao.impl.XmlDAOImpl;

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
