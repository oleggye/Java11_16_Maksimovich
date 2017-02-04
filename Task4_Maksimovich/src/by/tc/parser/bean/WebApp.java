package by.tc.parser.bean;

import java.util.ArrayList;
import java.util.List;

public class WebApp {

	private String id;

	private String version;

	private List<DisplayName> displayNameCollection;

	private List<WelcomeFileList> welcomeFileListCollection;

	private List<Filter> filterCollection;

	private List<FilterMapping> filterMappingCollection;

	private List<Listener> listenerCollection;

	private List<Servlet> servletCollection;

	private List<ServletMapping> servletMappingCollection;

	private List<ErrorPage> errorPageList;

	public WebApp() {

		displayNameCollection = new ArrayList<>();
		welcomeFileListCollection = new ArrayList<>();
		filterCollection = new ArrayList<>();
		filterMappingCollection = new ArrayList<>();
		listenerCollection = new ArrayList<>();
		servletCollection = new ArrayList<>();
		servletMappingCollection = new ArrayList<>();
		errorPageList = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<DisplayName> getDisplayNameCollection() {
		return displayNameCollection;
	}

	public void setDisplayNameCollection(List<DisplayName> displayNameCollection) {
		this.displayNameCollection = displayNameCollection;
	}

	public List<WelcomeFileList> getWelcomeFileListCollection() {
		return welcomeFileListCollection;
	}

	public void setWelcomeFileListCollection(List<WelcomeFileList> welcomeFileListCollection) {
		this.welcomeFileListCollection = welcomeFileListCollection;
	}

	public List<Filter> getFilterCollection() {
		return filterCollection;
	}

	public void setFilterCollection(List<Filter> filterCollection) {
		this.filterCollection = filterCollection;
	}

	public List<FilterMapping> getFilterMappingCollection() {
		return filterMappingCollection;
	}

	public void setFilterMappingCollection(List<FilterMapping> filterMappingCollection) {
		this.filterMappingCollection = filterMappingCollection;
	}

	public List<Listener> getListenerCollection() {
		return listenerCollection;
	}

	public void setListenerCollection(List<Listener> listenerCollection) {
		this.listenerCollection = listenerCollection;
	}

	public List<Servlet> getServletCollection() {
		return servletCollection;
	}

	public void setServletCollection(List<Servlet> servletCollection) {
		this.servletCollection = servletCollection;
	}

	public List<ServletMapping> getServletMappingCollection() {
		return servletMappingCollection;
	}

	public void setServletMappingCollection(List<ServletMapping> servletMappingCollection) {
		this.servletMappingCollection = servletMappingCollection;
	}

	public List<ErrorPage> getErrorPageList() {
		return errorPageList;
	}

	public void setErrorPageList(List<ErrorPage> errorPageList) {
		this.errorPageList = errorPageList;
	}

	@Override
	public String toString() {
		return "WebApp [id=" + id + ", version=" + version + ",\n displayNameCollection=" + displayNameCollection
				+ ",\n welcomeFileListCollection=" + welcomeFileListCollection + ",\n filterCollection="
				+ filterCollection + ",\n filterMappingCollection=" + filterMappingCollection
				+ ",\n listenerCollection=" + listenerCollection + ",\n servletCollection=" + servletCollection
				+ ",\n servletMappingCollection=" + servletMappingCollection + ",\n errorPageList=" + errorPageList
				+ "]";
	}

}
