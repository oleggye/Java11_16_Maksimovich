package by.tc.parser.service.impl.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.tc.parser.bean.DisplayName;
import by.tc.parser.bean.ErrorPage;
import by.tc.parser.bean.Filter;
import by.tc.parser.bean.FilterMapping;
import by.tc.parser.bean.InitParam;
import by.tc.parser.bean.Listener;
import by.tc.parser.bean.Servlet;
import by.tc.parser.bean.ServletMapping;
import by.tc.parser.bean.WebApp;
import by.tc.parser.bean.WebAppTagName;
import by.tc.parser.bean.WelcomeFileList;
import by.tc.parser.service.WebAppBuilder;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.impl.sax.WebAppSAXBuilder;

public class WebAppStAXBuilder extends WebAppBuilder {

	private static final Logger logger = LogManager.getLogger(WebAppSAXBuilder.class.getName());

	private StringBuilder text;

	private DisplayName displayName;

	private WelcomeFileList welcomeFileList;

	private Filter filter;

	private InitParam initParam;

	private FilterMapping filterMapping;

	private Listener listener;

	private Servlet servlet;

	private ServletMapping servletMapping;

	private ErrorPage errorPage;

	private XMLInputFactory inputFactory;

	public WebAppStAXBuilder() {

		inputFactory = XMLInputFactory.newInstance();
		text = new StringBuilder();
	}

	public void buildWebApp(String fileName) throws ServiceException {

		FileInputStream inputStream = null;
		XMLStreamReader reader = null;

		try {

			inputStream = new FileInputStream(fileName);
			reader = inputFactory.createXMLStreamReader(inputStream);

			buildWebApp(reader);

		} catch (FileNotFoundException e) {
			throw new ServiceException("No such file= '" + fileName + "'", e);
		} catch (XMLStreamException e) {
			throw new ServiceException("An exception occured during parsing", e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (XMLStreamException e) {
				logger.error("XMLStreamReader closing error", e);
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (IOException e) {
					logger.error("FileInputStream closing error", e);
				}
			}
		}
	}

	private void buildWebApp(XMLStreamReader reader) throws XMLStreamException {

		String tagName;

		while (reader.hasNext()) {
			int type = reader.next();

			switch (type) {

			case XMLStreamConstants.START_ELEMENT:
				tagName = reader.getLocalName();
				startElement(tagName, reader);
				break;

			case XMLStreamConstants.CHARACTERS:
				characters(reader.getText());
				break;

			case XMLStreamConstants.END_ELEMENT:
				tagName = reader.getLocalName();
				endElement(tagName, reader);
				break;

			}
		}
	}

	private void startElement(String localName, XMLStreamReader reader) throws XMLStreamException {

		WebAppTagName webAppTagName;

		try {
			webAppTagName = WebAppTagName.localNameToTagName(localName);
		} catch (IllegalArgumentException e) {
			logger.error("Wrong startTag name= '" + localName + "'", e);
			throw new XMLStreamException(e);
		}

		switch (webAppTagName) {

		case WEB_APP:
			webApp = new WebApp();
			webApp.setId(reader.getAttributeValue(null, "id"));
			webApp.setVersion(reader.getAttributeValue(null, "version"));
			break;

		case DISPLAY_NAME:
			displayName = new DisplayName();
			break;

		case WELCOME_FILE_LIST:
			welcomeFileList = new WelcomeFileList();
			break;

		case FILTER:
			filter = new Filter();
			break;

		case INIT_PARAM:
			initParam = new InitParam();
			break;

		case FILTER_MAPPING:
			filterMapping = new FilterMapping();
			break;

		case LISTENER:
			listener = new Listener();
			break;

		case SERVLET:
			servlet = new Servlet();
			break;

		case SERVLET_MAPPING:
			servletMapping = new ServletMapping();
			break;

		case ERROR_PAGE:
			errorPage = new ErrorPage();
			break;

		default:
			break;
		}
	}

	private void characters(String text) {
		this.text.append(text.trim());
	}

	private void endElement(String localName, XMLStreamReader reader) throws XMLStreamException {

		WebAppTagName webAppTagName = WebAppTagName.localNameToTagName(localName);

		switch (webAppTagName) {

		case DISPLAY_NAME:
			displayName.setText(text.toString());
			webApp.getDisplayNameCollection().add(displayName);
			break;

		case WELCOME_FILE_LIST:
			webApp.getWelcomeFileListCollection().add(welcomeFileList);
			break;

		case WELCOME_FILE:
			welcomeFileList.setWelcomeFile(text.toString());
			break;

		case FILTER:
			webApp.getFilterCollection().add(filter);
			filter = null;
			break;

		case FILTER_NAME:
			if (filter != null) {
				filter.setFilterName(text.toString());
			} else {
				filterMapping.setFilterName(text.toString());
			}
			break;

		case FILTER_CLASS:
			filter.setFilterClass(text.toString());
			break;

		case INIT_PARAM:
			if (filter != null) {
				filter.getInitParamList().add(initParam);
			} else {
				servlet.getInitParamList().add(initParam);
			}
			break;

		case PARAM_NAME:
			initParam.setParamName(text.toString());
			break;

		case PARAM_VALUE:
			initParam.setParamValue(text.toString());
			break;

		case FILTER_MAPPING:
			webApp.getFilterMappingCollection().add(filterMapping);
			filterMapping = null;
			break;

		case URL_PATTERN:
			if (filterMapping != null) {
				filterMapping.setUrlPattern(text.toString());
			} else {
				servletMapping.setUrlPattern(text.toString());
			}
			break;

		case DISPATCHER:
			filterMapping.setDispatcher(text.toString());
			break;

		case LISTENER:
			webApp.getListenerCollection().add(listener);
			break;

		case LISTENER_CLASS:
			listener.setListenerClass(text.toString());
			break;

		case SERVLET:
			webApp.getServletCollection().add(servlet);
			servlet = null;
			break;

		case SERVLET_NAME:
			if (servlet != null) {
				servlet.setServletName(text.toString());
			} else {
				servletMapping.setServletName(text.toString());
			}
			break;

		case SERVLET_CLASS:
			servlet.setServletClass(text.toString());
			break;

		case SERVLET_MAPPING:
			webApp.getServletMappingCollection().add(servletMapping);
			servletMapping = null;
			break;

		case ERROR_PAGE:
			webApp.getErrorPageList().add(errorPage);
			break;

		case EXCEPTION_TYPE:
			errorPage.setExceptionType(text.toString());
			break;

		case ERROR_CODE:
			errorPage.setErrorCode(text.toString());
			break;

		case LOCATION:
			errorPage.setLocation(text.toString());
			break;

		default:
			break;
		}

		text.setLength(0);
	}
}
