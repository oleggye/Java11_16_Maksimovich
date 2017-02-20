package by.epam.totalizator.service.factory;

import by.epam.totalizator.service.BettingService;
import by.epam.totalizator.service.ClientService;
import by.epam.totalizator.service.CompetitionService;
import by.epam.totalizator.service.impl.BettingServiceImpl;
import by.epam.totalizator.service.impl.ClientServiceImpl;
import by.epam.totalizator.service.impl.CompetitionServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final BettingService bettingService = new BettingServiceImpl();
	private final ClientService clientService = new ClientServiceImpl();
	private final CompetitionService competitionService = new CompetitionServiceImpl();

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
}
