package by.tc.eq.dao;

import java.util.List;

import by.tc.eq.bean.Equipment;
import by.tc.eq.dao.exeption.DAOException;

public interface EquipmentDAO {

	public void addEquipment(Equipment equipment) throws DAOException;

	public void deleteEquipment(int id_equipment) throws DAOException;

	public void updateEquipment(Equipment equipment) throws DAOException;

	public Equipment getEquipment(int id) throws DAOException;

	public List<Equipment> getAllAvailableEquipment() throws DAOException;

	public List<Equipment> getAllRentedEquipment() throws DAOException;
}
