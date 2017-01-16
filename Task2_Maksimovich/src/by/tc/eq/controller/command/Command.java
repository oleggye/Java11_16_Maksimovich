package by.tc.eq.controller.command;

public interface Command {

	static final String PARAM_DELIMETER = ";";

	public String execute(String request);

}
