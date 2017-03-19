package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.dao.exception.DAOException;

public interface CountryDAO {
	
	public List<Country> obtainCountryList() throws DAOException;

}
