package by.training.analyser.service.factory;

import by.training.analyser.service.AnalyseService;
import by.training.analyser.service.impl.AnalyseServiceImpl;

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
