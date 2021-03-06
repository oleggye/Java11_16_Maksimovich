package by.tc.eq.service.impl;

import java.util.Arrays;
import java.util.List;

import by.tc.eq.bean.Equipment;
import by.tc.eq.bean.Ower;
import by.tc.eq.bean.User;
import by.tc.eq.dao.exeption.DAOException;
import by.tc.eq.dao.factory.DAOFactory;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.util.ServiceInspector;

public class ShopServiceImpl implements ShopService {

	@Override
	public List<Ower> getOwerReport() throws ServiceException {

		DAOFactory df = DAOFactory.getInstance();

		try {

			List<Ower> list = df.getRentDAO().getAllOwers();
			return list;

		} catch (DAOException e) {
			throw new ServiceException("Ошибка при получении отчета", e);
		}

	}

	@Override
	public List<User> getUsersReport() throws ServiceException {

		DAOFactory df = DAOFactory.getInstance();

		try {

			List<User> list = df.getUserDAO().getAllUsers();
			return list;

		} catch (DAOException e) {
			throw new ServiceException("Ошибка при получении отчета", e);
		}
	}

	@Override
	public List<Equipment> getAvailableEquipmentReport() throws ServiceException {

		DAOFactory df = DAOFactory.getInstance();

		try {

			List<Equipment> list = df.getEquipmentsDAO().getAllAvailableEquipment();
			return list;

		} catch (DAOException e) {
			throw new ServiceException("Ошибка при получении отчета", e);
		}

	}

	@Override
	public List<Equipment> getRentedEquipmentReport() throws ServiceException {

		DAOFactory df = DAOFactory.getInstance();

		try {

			List<Equipment> list = df.getEquipmentsDAO().getAllRentedEquipment();
			return list;

		} catch (DAOException e) {
			throw new ServiceException("Ошибка при получении отчета", e);
		}

	}

	@Override
	public void addEquipment(Equipment equipment) throws ServiceException {

		if (ServiceInspector.isEquipmentObjectIncorrect(equipment)) {

			throw new ServiceException("Введены некорректные данные о снаряжении: " + equipment);

		} else {

			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getEquipmentsDAO().addEquipment(equipment);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при добавлении снаряжениия: " + equipment, e);
			}
		}

	}

	@Override
	public void updateEquipment(Equipment equipment) throws ServiceException {

		/**
		 * добавлена проверка (equipment.getId() > 0), т.к. в данном методе мы
		 * пытаемся обновить данные о существующем снаряжения, то нам необходимо
		 * проверить на предварительную корректность поле id объекта equipment.
		 */

		if (ServiceInspector.isEquipmentObjectIncorrect(equipment) && equipment.getId() < 0) {

			throw new ServiceException("Введены некорректные данные о снаряжении: " + equipment);

		} else {
			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getEquipmentsDAO().updateEquipment(equipment);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при обновлении данных о снаряжении: " + equipment, e);
			}
		}
	}

	@Override
	public void deleteEquipment(int equipmentId) throws ServiceException {

		if (equipmentId < 0) {

			throw new ServiceException("Введены некорректные данные о снаряжении: equipment_id :" + equipmentId + "'");

		} else {

			DAOFactory df = DAOFactory.getInstance();

			try {
				df.getEquipmentsDAO().deleteEquipment(equipmentId);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при удалении снаряжения: equipment_id : '" + equipmentId + "'", e);
			}
		}

	}

	@Override
	public Equipment getEquipmentInfo(int equipmentId) throws ServiceException {

		if (equipmentId < 0) {

			throw new ServiceException("Введены некорректные данные о снаряжении: " + equipmentId + "'");

		} else {

			DAOFactory df = DAOFactory.getInstance();

			try {

				return df.getEquipmentsDAO().getEquipment(equipmentId);

			} catch (DAOException e) {
				throw new ServiceException(
						"Ошибка при попытке получить информацию о снаряжении: equipment_id : '" + equipmentId + "'",
						e);
			}
		}
	}

	@Override
	public void rentEquipment(int userId, int[] equipmentIds) throws ServiceException {

		if (userId < 0 || ServiceInspector.isIdArrayIncorrect((equipmentIds))) {

			throw new ServiceException("Введены некорректные данные: id_user: '" + userId + "' , ids_equipment: "
					+ Arrays.toString((equipmentIds)));
		} else {
			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getRentDAO().rentEquipment(userId, equipmentIds);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при попытке арендавать снаряжение:  id_user: '" + userId
						+ "' , ids_equipment: " + Arrays.toString(equipmentIds), e);
			}
		}
	}

	@Override
	public void returnEquipment(int userId, int[] equipmentIds) throws ServiceException {

		if (userId < 0 || ServiceInspector.isIdArrayIncorrect((equipmentIds))) {

			throw new ServiceException("Введены некорректные данные: id_user: '" + userId + "' , ids_equipment: "
					+ Arrays.toString((equipmentIds)));

		} else {
			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getRentDAO().returnEquipment(userId, equipmentIds);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при попытке вернуть снаряжение:  id_user: '" + userId
						+ "' , ids_equipment: " + Arrays.toString(equipmentIds), e);
			}
		}
	}
}