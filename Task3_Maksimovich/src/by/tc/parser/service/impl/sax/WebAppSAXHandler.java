package by.tc.parser.service.impl.sax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

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

public class WebAppSAXHandler extends DefaultHandler {

	private static final Logger logger = LogManager.getLogger(WebAppSAXHandler.class.getName());

	private StringBuilder text;

	private WebApp webApp;

	private DisplayName displayName;

	private WelcomeFileList welcomeFileList;

	private Filter filter;

	private InitParam initParam;

	private FilterMapping filterMapping;

	private Listener listener;

	private Servlet servlet;

	private ServletMapping servletMapping;

	private ErrorPage errorPage;

	public WebAppSAXHandler() {
		text = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		WebAppTagName webAppTagName;

		try {
			webAppTagName = WebAppTagName.localNameToTagName(localName);
		} catch (IllegalArgumentException e) {
			logger.error("Wrong startTag name= '" + localName + "'", e);
			throw new SAXException(e);
		}

		switch (webAppTagName) {

		case WEB_APP:

			webApp = new WebApp();
			webApp.setId(attributes.getValue("id"));
			webApp.setVersion(attributes.getValue("version"));

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

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO: пришлось создавать новую строку для trim(), т.к. попадали
		// служебные символы
		text.append(new String(ch, start, length).trim());
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		/**
		 * здесь нет смысла проверять на правильность закрывающий тег, т.к. это
		 * сделает раньше парсер
		 */
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

	@Override
	public void warning(SAXParseException e) throws SAXException {
		logger.warn("A warning during parsing process", e);
		System.out.println("warning");
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		logger.error("A error during parsing process", e);
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		logger.fatal("A fatal error during parsing process", e);
		super.fatalError(e);
	}

	public WebApp getWebApp() {
		return webApp;
	}

}
