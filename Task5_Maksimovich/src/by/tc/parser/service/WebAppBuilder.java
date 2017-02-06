package by.tc.parser.service;

import by.tc.parser.bean.WebApp;
import by.tc.parser.service.exception.ServiceException;

public abstract class WebAppBuilder {

	protected WebApp webApp;

	public WebAppBuilder() {
		webApp = new WebApp();
	}

	public WebApp getWebApp() {
		return webApp;
	}

	public abstract void buildWebApp(String fileName) throws ServiceException;
}
