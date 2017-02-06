package by.tc.parser.bean;

import java.io.Serializable;

public class FilterMapping implements Serializable {

	private static final long serialVersionUID = 1947394042817859798L;

	private String filterName;

	private String urlPattern;

	private String dispatcher;

	public FilterMapping() {

	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public String toString() {
		return "filterName= '" + filterName + "'; urlPattern= '" + urlPattern + "'; dispatcher= '" + dispatcher + "';";
	}

}
