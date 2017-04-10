package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.service.exception.ServiceException;

public interface SportService {

	List<Sport> obtainSportList(Locale locale) throws ServiceException;

}
