package by.tc.parser.bean;

import java.io.Serializable;

public class ServletMapping implements Serializable {

	private static final long serialVersionUID = -9020029126482040978L;

	private String servletName;

	private String urlPattern;

	public ServletMapping() {
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	@Override
	public String toString() {
		return "servletName= '" + servletName + "'; urlPattern= '" + urlPattern + "';";
	}

}
