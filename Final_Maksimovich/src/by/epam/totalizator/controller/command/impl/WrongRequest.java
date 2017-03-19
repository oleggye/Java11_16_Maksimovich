package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;

public class WrongRequest implements Command {

	private static final String ERROR_PAGE_URL = "path.page.error";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(ERROR_PAGE_URL);
		return page;
	}

}
