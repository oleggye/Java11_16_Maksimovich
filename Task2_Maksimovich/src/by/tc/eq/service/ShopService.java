package by.tc.eq.service;

import java.util.List;

import by.tc.eq.bean.Equipment;
import by.tc.eq.bean.Ower;
import by.tc.eq.bean.User;
import by.tc.eq.service.exception.ServiceException;

public interface ShopService {

	// Reports
	List<Ower> getOwerReport() throws ServiceException;

	List<User> getUsersReport() throws ServiceException;

	List<Equipment> getAvailableEquipmentReport() throws ServiceException;

	List<Equipment> getRentedEquipmentReport() throws ServiceException;

	// Equipment

	void addEquipment(Equipment equipment) throws ServiceException;

	void updateEquipment(Equipment equipment) throws ServiceException;

	void deleteEquipment(int equipmentId) throws ServiceException;

	Equipment getEquipmentInfo(int equipmentId) throws ServiceException;

	// Rent

	void rentEquipment(int userId, int[] equipmentIds) throws ServiceException;

	void returnEquipment(int userId, int[] equipmentIds) throws ServiceException;
}
