package by.tc.parser.service.impl.sax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.tc.parser.service.WebAppBuilder;
import by.tc.parser.service.exception.ServiceException;

public class WebAppSAXBuilder extends WebAppBuilder {

	private static final Logger logger = LogManager.getLogger(WebAppSAXBuilder.class.getName());

	private WebAppSAXHandler webSaxHandler;

	private XMLReader reader;

	public WebAppSAXBuilder() {

		webSaxHandler = new WebAppSAXHandler();

		try {

			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(webSaxHandler);
		} catch (SAXException e) {
			logger.error("Can't create XMLReader", e);

		}
	}

	public void buildWebApp(String fileName) throws ServiceException {

		try {

			InputSource inputSource = new InputSource(fileName);
			reader.parse(inputSource);

		} catch (IOException e) {
			throw new ServiceException("A problem with file= '" + fileName + "'", e);
		} catch (SAXException e) {
			throw new ServiceException("An exception occured during parsing", e);
		}

		webApp = webSaxHandler.getWebApp();
	}

}
