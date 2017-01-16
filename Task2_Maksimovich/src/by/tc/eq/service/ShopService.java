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

	void deleteEquipment(int id_equipment) throws ServiceException;

	Equipment getEquipmentInfo(int id_equipment) throws ServiceException;

	// Rent

	void rentEquipment(int id_user, int[] ids_equipment) throws ServiceException;

	void returnEquipment(int id_user, int[] ids_equipment) throws ServiceException;
}
