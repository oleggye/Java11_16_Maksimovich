package by.tc.parser.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Servlet implements Serializable {

	private static final long serialVersionUID = 3925104024380247923L;

	private String servletName;

	private String servletClass;

	private List<InitParam> initParamList;

	public Servlet() {
		initParamList = new ArrayList<>();
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getServletClass() {
		return servletClass;
	}

	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}

	public List<InitParam> getInitParamList() {
		return initParamList;
	}

	public void setInitParamList(List<InitParam> initParamList) {
		this.initParamList = initParamList;
	}

	public InitParam getInitParam(int index) {
		return initParamList.get(index);
	}

	public void setInitParam(int index, InitParam initParam) {
		initParamList.set(index, initParam);
	}

	public void setInitParam(InitParam initParam) {
		initParamList.add(initParam);
	}

	@Override
	public String toString() {
		return "servletName= '" + servletName + "'; servletClass= '" + servletClass + "'; initParamList= "
				+ initParamList + ";";
	}

}
