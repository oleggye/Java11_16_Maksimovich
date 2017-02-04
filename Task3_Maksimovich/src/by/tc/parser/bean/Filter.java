package by.tc.parser.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Filter implements Serializable {

	private static final long serialVersionUID = -9088016664756347864L;

	private String filterName;

	private String filterClass;

	private List<InitParam> initParamList;

	public Filter() {
		initParamList = new ArrayList<>();
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterClass() {
		return filterClass;
	}

	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
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
		return "filterName= '" + filterName + "'; filterClass= '" + filterClass + "'; initParamList= " + initParamList
				+ ";";
	}

}
