package by.tc.parser.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WelcomeFileList implements Serializable {

	private static final long serialVersionUID = 7249685077442331613L;

	List<String> welcomeFileList;

	public WelcomeFileList() {
		welcomeFileList = new ArrayList<>();
	}

	public List<String> getWelcomeFileList() {
		return welcomeFileList;
	}

	public void setWelcomeFileList(List<String> welcomeFiles) {
		this.welcomeFileList = welcomeFiles;
	}

	public String getWelcomeFile(int index) {
		return welcomeFileList.get(index);
	}

	public void setWelcomeFile(int index, String welcomeFile) {
		welcomeFileList.set(index, welcomeFile);
	}

	public void setWelcomeFile(String welcomeFile) {
		welcomeFileList.add(welcomeFile);
	}

	@Override
	public String toString() {
		StringBuilder resultString = new StringBuilder();

		for (String elem : welcomeFileList) {
			resultString.append("welcomeFile= '");
			resultString.append(elem);
			resultString.append("'; ");
		}
		return resultString.toString();
	}
}
