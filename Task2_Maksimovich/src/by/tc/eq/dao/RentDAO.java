package by.tc.eq.dao;

import java.util.List;

import by.tc.eq.bean.Ower;
import by.tc.eq.dao.exeption.DAOException;

public interface RentDAO {

	public void rentEquipment(int id, int[] ids_equipment) throws DAOException;

	public void returnEquipment(int id, int[] ids_equipment) throws DAOException;

	public List<Ower> getAllOwers() throws DAOException;
}
