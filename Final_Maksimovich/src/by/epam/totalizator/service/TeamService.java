package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Team;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface TeamService {

	List<Team> obtainTeamListByIdSport(int idSport, Locale locale) throws ServiceException, ServiceValidationException;
}
