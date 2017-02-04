package by.tc.parser.bean;

public enum WebAppTagName {

	WEB_APP,

	DISPLAY_NAME, WELCOME_FILE_LIST, WELCOME_FILE,

	FILTER, FILTER_NAME, FILTER_CLASS,

	INIT_PARAM, PARAM_NAME, PARAM_VALUE,

	FILTER_MAPPING, URL_PATTERN, DISPATCHER,

	LISTENER, LISTENER_CLASS,

	SERVLET, SERVLET_NAME, SERVLET_CLASS,

	SERVLET_MAPPING,

	ERROR_PAGE, EXCEPTION_TYPE, ERROR_CODE, LOCATION;

	public static String tagNameToLocalName(WebAppTagName enumName) {
		return enumName.name().toLowerCase().replace("_", "-");
	}

	public static WebAppTagName localNameToTagName(String tagName) {
		return valueOf(tagName.toUpperCase().replace("-", "_"));
	}
}
