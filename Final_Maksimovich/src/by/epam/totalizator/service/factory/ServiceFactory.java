package by.epam.totalizator.service.factory;

import by.epam.totalizator.service.BettingService;
import by.epam.totalizator.service.ClientService;
import by.epam.totalizator.service.CompetitionService;
import by.epam.totalizator.service.CountryService;
import by.epam.totalizator.service.SportService;
import by.epam.totalizator.service.TeamService;
import by.epam.totalizator.service.TournamentService;
import by.epam.totalizator.service.ValidationService;
import by.epam.totalizator.service.impl.BettingServiceImpl;
import by.epam.totalizator.service.impl.ClientServiceImpl;
import by.epam.totalizator.service.impl.CompetitionServiceImpl;
import by.epam.totalizator.service.impl.CountryServiceImpl;
import by.epam.totalizator.service.impl.SportServiceImpl;
import by.epam.totalizator.service.impl.TeamServiceImpl;
import by.epam.totalizator.service.impl.TournamentServiceImpl;
import by.epam.totalizator.service.impl.ValidationServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final BettingService bettingService = new BettingServiceImpl();
	private final ClientService clientService = new ClientServiceImpl();
	private final CompetitionService competitionService = new CompetitionServiceImpl();
	private final TournamentService tournamentService = new TournamentServiceImpl();
	private final TeamService teamService = new TeamServiceImpl();
	private final CountryService countryService = new CountryServiceImpl();
	private final ValidationService validationService = new ValidationServiceImpl();
	private final SportService sportService = new SportServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public BettingService getBettingService() {
		return bettingService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public CompetitionService getCompetitionService() {
		return competitionService;
	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public ValidationService getValidationService() {
		return validationService;
	}
	
	public SportService getSportService() {
		return sportService;
	}
}
