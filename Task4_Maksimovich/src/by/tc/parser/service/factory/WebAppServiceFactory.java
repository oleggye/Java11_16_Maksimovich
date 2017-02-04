package by.tc.parser.service.factory;

import by.tc.parser.service.WebAppBuilder;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.impl.dom.WebAppDOMBuilder;
import by.tc.parser.service.impl.sax.WebAppSAXBuilder;
import by.tc.parser.service.impl.stax.WebAppStAXBuilder;

public class WebAppServiceFactory {

	private static final WebAppServiceFactory instance = new WebAppServiceFactory();

	private WebAppServiceFactory() {
	}

	public static WebAppServiceFactory getInstance() {
		return instance;
	}

	private enum ParserType {
		SAX, STAX, DOM;
	}

	public WebAppBuilder getWebAppBuilder(String parserType) throws ServiceException {

		try {

			ParserType type = ParserType.valueOf(parserType.toUpperCase());

			switch (type) {
			case SAX:
				return new WebAppSAXBuilder();
			case STAX:
				return new WebAppStAXBuilder();
			case DOM:
				return new WebAppDOMBuilder();
			}
		} catch (java.lang.IllegalArgumentException e) {
			throw new ServiceException("Bad parameter: parserType= '" + parserType, e);
		}
		return null;
	}

}
