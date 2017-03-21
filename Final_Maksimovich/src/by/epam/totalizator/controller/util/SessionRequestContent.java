package by.epam.totalizator.controller.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionRequestContent {

	private Map<String, Object> requestAttributeMap;
	private Map<String, String[]> requestParameterMap;
	private Map<String, Object> sessionAttributeMap;

	public void extractValues(HttpServletRequest request) {
		requestParameterMap = request.getParameterMap();

//		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
//			System.out.println(entry.getKey() + "" + entry.getValue());
//		}

		sessionAttributeMap = new HashMap<>();

		HttpSession session = request.getSession();
		Enumeration<String> enumeration = session.getAttributeNames();

		while (enumeration.hasMoreElements()) {

			String key = enumeration.nextElement();
			Object value = session.getValue(key);
			sessionAttributeMap.put(key, value);
		}

	}

	public void insertAttributeMap(HttpServletRequest request) {

		if (requestAttributeMap != null) {
			for (Map.Entry<String, Object> entry : requestAttributeMap.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
		}

		if (sessionAttributeMap != null) {
			for (Map.Entry<String, Object> entry : sessionAttributeMap.entrySet()) {
				request.getSession().setAttribute(entry.getKey(), entry.getValue());
			}
		}
	}

	public String getRequestParam(Object key) {

		String[] paramValue = requestParameterMap.get(key);
		if (paramValue != null && paramValue.length == 1) {
			return paramValue[0];
		} else {
			return null;
		}
	}

	public Object getSessionParam(Object key) {

		Object paramValue = sessionAttributeMap.get(key);
		if (paramValue != null) {
			return paramValue;
		} else {
			return null;
		}
	}

	public void putRequestAttribute(String key, Object value) {
		if (requestAttributeMap == null) {
			requestAttributeMap = new HashMap<>();
		}
		requestAttributeMap.put(key, value);
	}

	public void putSessionAttribute(String key, Object value) {
		if (sessionAttributeMap == null) {
			sessionAttributeMap = new HashMap<>();
		}
		sessionAttributeMap.put(key, value);
	}

}
