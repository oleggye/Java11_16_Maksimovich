package by.epam.totalizator.service.factory;

import by.epam.totalizator.service.IBettingService;
import by.epam.totalizator.service.IClientService;
import by.epam.totalizator.service.ICompetitionService;
import by.epam.totalizator.service.ICountryService;
import by.epam.totalizator.service.ISportService;
import by.epam.totalizator.service.ITeamService;
import by.epam.totalizator.service.ITournamentService;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.impl.BettingServiceImpl;
import by.epam.totalizator.service.impl.ClientServiceImpl;
import by.epam.totalizator.service.impl.CompetitionServiceImpl;
import by.epam.totalizator.service.impl.CountryServiceImpl;
import by.epam.totalizator.service.impl.SportServiceImpl;
import by.epam.totalizator.service.impl.TeamServiceImpl;
import by.epam.totalizator.service.impl.TournamentServiceImpl;
import by.epam.totalizator.service.impl.ValidationServiceImpl;

/**
 * Factory pattern implementation for service layer
 */
public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final IBettingService bettingService = new BettingServiceImpl();
	private final IClientService clientService = new ClientServiceImpl();
	private final ICompetitionService competitionService = new CompetitionServiceImpl();
	private final ITournamentService tournamentService = new TournamentServiceImpl();
	private final ITeamService teamService = new TeamServiceImpl();
	private final ICountryService countryService = new CountryServiceImpl();
	private final IValidationService validationService = new ValidationServiceImpl();
	private final ISportService sportService = new SportServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public IBettingService getBettingService() {
		return bettingService;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public ICompetitionService getCompetitionService() {
		return competitionService;
	}

	public ITournamentService getTournamentService() {
		return tournamentService;
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public ICountryService getCountryService() {
		return countryService;
	}

	public IValidationService getValidationService() {
		return validationService;
	}

	public ISportService getSportService() {
		return sportService;
	}
}