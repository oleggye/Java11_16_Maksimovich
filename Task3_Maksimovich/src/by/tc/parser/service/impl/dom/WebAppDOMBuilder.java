package by.tc.parser.service.impl.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.tc.parser.bean.DisplayName;
import by.tc.parser.bean.ErrorPage;
import by.tc.parser.bean.Filter;
import by.tc.parser.bean.FilterMapping;
import by.tc.parser.bean.InitParam;
import by.tc.parser.bean.Listener;
import by.tc.parser.bean.Servlet;
import by.tc.parser.bean.ServletMapping;
import by.tc.parser.bean.WebAppTagName;
import by.tc.parser.bean.WelcomeFileList;
import by.tc.parser.service.WebAppBuilder;
import by.tc.parser.service.exception.ServiceException;

public class WebAppDOMBuilder extends WebAppBuilder {

	private DOMParser parser;

	public WebAppDOMBuilder() {

		parser = new DOMParser();
	}

	public void buildWebApp(String fileName) throws ServiceException {

		Document document = null;

		try {
			InputSource inputSource = new InputSource(fileName);
			parser.parse(inputSource);

			document = parser.getDocument();

			Element root = document.getDocumentElement();

			buildWebApp(root);

		} catch (IOException e) {
			throw new ServiceException("A problem with file= '" + fileName + "'", e);
		} catch (SAXException e) {
			throw new ServiceException("An exception occured during parsing", e);
		}
	}

	private void buildWebApp(Element webAppElement) {
		/**
		 * mainTags - массив родительских тегов, от которого отталкиваемся при
		 * разборе web.xml
		 */

		WebAppTagName[] mainTags = new WebAppTagName[] { WebAppTagName.DISPLAY_NAME, WebAppTagName.WELCOME_FILE_LIST,
				WebAppTagName.FILTER, WebAppTagName.FILTER_MAPPING, WebAppTagName.LISTENER, WebAppTagName.SERVLET,
				WebAppTagName.SERVLET_MAPPING, WebAppTagName.ERROR_PAGE };

		webApp.setId(webAppElement.getAttribute("id"));
		webApp.setVersion(webAppElement.getAttribute("version"));

		for (WebAppTagName tagName : mainTags) {

			String localName = WebAppTagName.tagNameToLocalName(tagName);

			NodeList childList = webAppElement.getElementsByTagName(localName);

			for (int index = 0; index < childList.getLength(); index++) {

				Element element = (Element) childList.item(index);

				switch (tagName) {

				case DISPLAY_NAME:
					buildDisplayName(element);
					break;

				case WELCOME_FILE_LIST:
					buildWelcomeFileList(element);
					break;

				case FILTER:
					buildFilter(element);
					break;

				case FILTER_MAPPING:
					buildFilterMapping(element);
					break;

				case LISTENER:
					buildListener(element);
					break;

				case SERVLET:
					buildServlet(element);
					break;

				case SERVLET_MAPPING:
					buildServletMapping(element);
					break;

				case ERROR_PAGE:
					buildErrorPage(element);
					break;

				default:
					break;
				}
			}
		}
	}

	private void buildDisplayName(Element displNameElement) {

		DisplayName displayName = new DisplayName();

		displayName.setText(displNameElement.getTextContent());

		webApp.getDisplayNameCollection().add(displayName);
	}

	private void buildWelcomeFileList(Element welcomeFileListElement) {

		String localName = WebAppTagName.tagNameToLocalName(WebAppTagName.WELCOME_FILE);
		NodeList childList = welcomeFileListElement.getElementsByTagName(localName);

		WelcomeFileList welcomeFileList = new WelcomeFileList();

		for (int index = 0; index < childList.getLength(); index++) {

			Node node = childList.item(index);

			welcomeFileList.setWelcomeFile(node.getTextContent());
		}

		webApp.getWelcomeFileListCollection().add(welcomeFileList);
	}

	private void buildFilter(Element filterElement) {

		Filter filter = new Filter();

		filter.setFilterName(getElementTextContent(filterElement, WebAppTagName.FILTER_NAME));
		filter.setFilterClass(getElementTextContent(filterElement, WebAppTagName.FILTER_CLASS));
		filter.setInitParamList(buildInitParams(filterElement));

		webApp.getFilterCollection().add(filter);
	}

	private List<InitParam> buildInitParams(Element parentElement) {

		List<InitParam> initParamList = new ArrayList<>();

		String localName = WebAppTagName.tagNameToLocalName(WebAppTagName.INIT_PARAM);
		// если нет совпадений данный метод вернет пустую коллекцию
		NodeList childList = parentElement.getElementsByTagName(localName);

		for (int index = 0; index < childList.getLength(); index++) {

			Element initElement = (Element) childList.item(index);

			InitParam initParam = new InitParam();

			initParam.setParamName(getElementTextContent(initElement, WebAppTagName.PARAM_NAME));
			initParam.setParamValue(getElementTextContent(initElement, WebAppTagName.PARAM_VALUE));

			initParamList.add(initParam);
		}

		return initParamList;
	}

	private void buildFilterMapping(Element buildFilterMappingElement) {

		FilterMapping filterMapping = new FilterMapping();

		filterMapping.setFilterName(getElementTextContent(buildFilterMappingElement, WebAppTagName.FILTER_NAME));
		filterMapping.setUrlPattern(getElementTextContent(buildFilterMappingElement, WebAppTagName.URL_PATTERN));
		filterMapping.setDispatcher(getElementTextContent(buildFilterMappingElement, WebAppTagName.DISPATCHER));

		webApp.getFilterMappingCollection().add(filterMapping);
	}

	private void buildListener(Element listenerElement) {

		Listener listener = new Listener();

		listener.setListenerClass(getElementTextContent(listenerElement, WebAppTagName.LISTENER_CLASS));

		webApp.getListenerCollection().add(listener);
	}

	private void buildServlet(Element servletElement) {

		Servlet servlet = new Servlet();

		servlet.setServletName(getElementTextContent(servletElement, WebAppTagName.SERVLET_NAME));
		servlet.setServletClass(getElementTextContent(servletElement, WebAppTagName.SERVLET_CLASS));
		servlet.setInitParamList(buildInitParams(servletElement));

		webApp.getServletCollection().add(servlet);
	}

	private void buildServletMapping(Element servletMappingElement) {

		ServletMapping servletMapping = new ServletMapping();

		servletMapping.setServletName(getElementTextContent(servletMappingElement, WebAppTagName.SERVLET_NAME));
		servletMapping.setUrlPattern(getElementTextContent(servletMappingElement, WebAppTagName.URL_PATTERN));

		webApp.getServletMappingCollection().add(servletMapping);
	}

	private void buildErrorPage(Element errorPageElement) {

		ErrorPage errorPage = new ErrorPage();

		errorPage.setExceptionType(getElementTextContent(errorPageElement, WebAppTagName.EXCEPTION_TYPE));
		errorPage.setErrorCode(getElementTextContent(errorPageElement, WebAppTagName.ERROR_CODE));
		errorPage.setLocation(getElementTextContent(errorPageElement, WebAppTagName.LOCATION));

		webApp.getErrorPageList().add(errorPage);
	}

	private static String getElementTextContent(Element element, WebAppTagName tagName) {

		String text = null;

		String elementName = WebAppTagName.tagNameToLocalName(tagName);

		NodeList childList = element.getElementsByTagName(elementName);

		if (childList.getLength() > 0) {
			Node node = childList.item(0);
			text = node.getTextContent().trim();
		}

		return text;
	}
}
