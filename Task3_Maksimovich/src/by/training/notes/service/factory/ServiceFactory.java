package by.training.notes.service.factory;

import by.training.notes.service.AnalyseService;
import by.training.notes.service.impl.AnalyseServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();

	private ServiceFactory() {
	}

	private AnalyseService analyseService = new AnalyseServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public AnalyseService getAnalyseService() {
		return analyseService;
	}
}
