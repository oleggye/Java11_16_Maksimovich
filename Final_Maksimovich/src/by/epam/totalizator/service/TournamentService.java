package by.epam.totalizator.service;

import java.util.List;
import java.util.Locale;

import by.epam.totalizator.bean.Tournament;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;

public interface TournamentService {

	List<Tournament> obtainTournamentListByIdSport(int idSport, Locale locale)
			throws ServiceException, ServiceValidationException;

}
