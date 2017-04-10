package by.epam.totalizator.dao;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.dao.exception.DAOException;

public interface SportDAO {

	List<Sport> obtainSportList(Locale locale) throws DAOException;
}
