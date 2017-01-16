package by.tc.eq.dao.factory;

import by.tc.eq.dao.EquipmentDAO;
import by.tc.eq.dao.RentDAO;
import by.tc.eq.dao.UserDAO;
import by.tc.eq.dao.impl.EquipmentDAOImpl;
import by.tc.eq.dao.impl.RentDAOImpl;
import by.tc.eq.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static final DAOFactory daoFactory = new DAOFactory();

	private UserDAO userDAO = new UserDAOImpl();
	private EquipmentDAO equipmentsDAO = new EquipmentDAOImpl();
	private RentDAO rentDAO = new RentDAOImpl();

	private DAOFactory() {
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public EquipmentDAO getEquipmentsDAO() {
		return equipmentsDAO;
	}

	public RentDAO getRentDAO() {
		return rentDAO;
	}

	public static DAOFactory getInstance() {
		return daoFactory;
	}

}
