package by.tc.eq.controller.util;

public class RequestParser {
	
	static final String PARAM_DELIMETER = ";";
	
	public static String getCommandNameFromRequest(String request){
		return request.substring(0, request.indexOf(PARAM_DELIMETER));
	}
	public static String[] getAllParamsFromRequest(String request){
		return request.split(PARAM_DELIMETER);
	}

}
