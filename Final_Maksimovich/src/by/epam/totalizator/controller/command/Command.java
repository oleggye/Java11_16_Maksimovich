package by.epam.totalizator.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

	public String execute(HttpServletRequest request);
}
